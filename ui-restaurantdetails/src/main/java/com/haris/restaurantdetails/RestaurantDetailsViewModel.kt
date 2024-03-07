package com.haris.restaurantdetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.haris.data.entities.MenuSubItem
import com.haris.data.entities.Result
import com.haris.restaurantdetails.data.MenuItemEntity
import com.haris.restaurantdetails.interactors.GetRestaurantDetailsInteractor
import com.haris.restaurantdetails.interactors.SearchMenuInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.annotation.concurrent.Immutable
import javax.inject.Inject

const val ID = "id"

@HiltViewModel
internal class RestaurantDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getRestaurantDetailsInteractor: GetRestaurantDetailsInteractor,
    private val searchMenuInteractor: SearchMenuInteractor
) : ViewModel() {

    private val id: String? = savedStateHandle.get<String>(ID)

    private val isSearching = MutableStateFlow(false)
    private val termState = MutableStateFlow("")

    init {
        if (id != null) {
            viewModelScope.launch {
                getRestaurantDetailsInteractor(id)
            }
        }

        search()
    }

    val state: StateFlow<RestaurantDetailsViewState> =
        combine(
            getRestaurantDetailsInteractor.flow,
            isSearching,
            termState,
            searchMenuInteractor.flow
        ) { restaurantDetailsResult, isSearching, term, searchedMenu ->
            val data = restaurantDetailsResult.data?.copy(searchedMenu = searchedMenu)
            when (restaurantDetailsResult) {
                is Result.Success -> {
                    if (data != null) {
                        RestaurantDetailsViewState.Success(
                            isSearching = isSearching,
                            term = term,
                            data = data,
                        )
                    } else {
                        RestaurantDetailsViewState.Error(
                            message = restaurantDetailsResult.message ?: "",
                            data = null
                        )
                    }
                }

                is Result.Loading -> {
                    RestaurantDetailsViewState.Loading(data)
                }

                is Result.Error -> {
                    RestaurantDetailsViewState.Error(
                        message = restaurantDetailsResult.message ?: "",
                        data = data
                    )
                }

                is Result.None -> RestaurantDetailsViewState.Empty
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = RestaurantDetailsViewState.Empty
        )

    fun search(term: String = "") {
        termState.value = term
        viewModelScope.launch {
            searchMenuInteractor(term)
        }
    }

    fun isSearching(value: Boolean) {
        isSearching.value = value
    }

    fun retry() {
        if (id != null) {
            viewModelScope.launch {
                getRestaurantDetailsInteractor(id)
            }
        }
    }
}

@Immutable
internal sealed interface RestaurantDetailsViewState {

    data class Success(
        val isSearching: Boolean,
        val term: String,
        val data: RestaurantDetailsEntity,
    ) : RestaurantDetailsViewState

    data class Error(
        val message: String, val data: RestaurantDetailsEntity?
    ) : RestaurantDetailsViewState

    data class Loading(val data: RestaurantDetailsEntity?) : RestaurantDetailsViewState

    data object Empty : RestaurantDetailsViewState
}

@Immutable
internal data class RestaurantDetailsEntity(
    val id: String,
    val name: String,
    val url: String,
    val rating: String,
    val numberOfRatings: String,
    val time: String,
    val distance: String,
    val menuItems: List<MenuItemEntity>,
    val searchedMenu: List<MenuSubItem> = emptyList()
)
