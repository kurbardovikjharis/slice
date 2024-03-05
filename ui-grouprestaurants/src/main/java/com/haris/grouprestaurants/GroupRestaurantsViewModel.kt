package com.haris.grouprestaurants

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.haris.data.Restaurant
import com.haris.data.Result
import com.haris.grouprestaurants.interactors.GetGroupRestaurantsInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.annotation.concurrent.Immutable
import javax.inject.Inject

const val ID = "id"
const val NAME = "name"

@HiltViewModel
internal class GroupRestaurantsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getGroupRestaurantsInteractor: GetGroupRestaurantsInteractor,
) : ViewModel() {

    private val id: String? = savedStateHandle.get<String>(ID)
    private val name: String? = savedStateHandle.get<String>(NAME)

    init {
        if (id != null) {
            viewModelScope.launch {
                getGroupRestaurantsInteractor(id)
            }
        }
    }

    val state: StateFlow<GroupRestaurantsViewState> = getGroupRestaurantsInteractor.flow.map {
        when (it) {
            is Result.Success -> {
                GroupRestaurantsViewState.Success(
                    title = it.data?.name ?: name ?: "",
                    restaurants = it.data?.restaurants ?: emptyList()
                )
            }

            is Result.Loading -> {
                GroupRestaurantsViewState.Loading(
                    title = it.data?.name ?: name ?: "",
                    restaurants = it.data?.restaurants
                )
            }

            is Result.Error -> {
                GroupRestaurantsViewState.Error(
                    message = it.message ?: "",
                    title = it.data?.name ?: name ?: "",
                    restaurants = it.data?.restaurants
                )
            }

            is Result.None -> GroupRestaurantsViewState.Empty
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = GroupRestaurantsViewState.Empty
    )

    fun retry() {
        if (id != null) {
            viewModelScope.launch {
                getGroupRestaurantsInteractor(id)
            }
        }
    }
}

@Immutable
internal sealed class GroupRestaurantsViewState(
    open val title: String = ""
) {

    data class Success(
        override val title: String,
        val restaurants: List<Restaurant>
    ) : GroupRestaurantsViewState(title)

    data class Error(
        val message: String,
        override val title: String,
        val restaurants: List<Restaurant>?
    ) : GroupRestaurantsViewState(title)

    data class Loading(
        override val title: String,
        val restaurants: List<Restaurant>?
    ) : GroupRestaurantsViewState(title)

    data object Empty : GroupRestaurantsViewState()
}

