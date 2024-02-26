package com.haris.restaurantdetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.haris.data.Result
import com.haris.restaurantdetails.data.MenuItemEntity
import com.haris.restaurantdetails.interactors.GetRestaurantDetailsInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.annotation.concurrent.Immutable
import javax.inject.Inject

const val ID = "id"

@HiltViewModel
internal class RestaurantDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getSensorDetailsInteractor: GetRestaurantDetailsInteractor
) : ViewModel() {

    private val id: String? = savedStateHandle.get<String>(ID)

    init {
        if (id != null) {
            viewModelScope.launch {
                getSensorDetailsInteractor(id)
            }
        }
    }

    val state: StateFlow<RestaurantDetailsViewState> = getSensorDetailsInteractor.flow.map {
        val data = it.data
        when (it) {
            is Result.Success -> {
                if (data != null) {
                    RestaurantDetailsViewState.Success(data)
                } else {
                    RestaurantDetailsViewState.Error(
                        message = it.message ?: "",
                        data = null
                    )
                }
            }

            is Result.Loading -> {
                RestaurantDetailsViewState.Loading(data)
            }

            is Result.Error -> {
                RestaurantDetailsViewState.Error(
                    message = it.message ?: "",
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

    fun retry() {
        if (id != null) {
            viewModelScope.launch {
                getSensorDetailsInteractor(id)
            }
        }
    }
}

@Immutable
internal sealed interface RestaurantDetailsViewState {

    data class Success(
        val data: RestaurantDetailsEntity
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
    val menuItems: List<MenuItemEntity>
)
