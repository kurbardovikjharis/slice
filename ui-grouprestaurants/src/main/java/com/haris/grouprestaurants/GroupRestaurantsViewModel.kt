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

@HiltViewModel
internal class GroupRestaurantsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getGroupRestaurantsInteractor: GetGroupRestaurantsInteractor,
) : ViewModel() {

    private val id: String? = savedStateHandle.get<String>(ID)

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
                    restaurants = it.data ?: emptyList()
                )
            }

            is Result.Loading -> {
                GroupRestaurantsViewState.Loading(
                    restaurants = it.data
                )
            }

            is Result.Error -> {
                GroupRestaurantsViewState.Error(
                    message = it.message ?: "",
                    restaurants = it.data
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
internal sealed interface GroupRestaurantsViewState {

    data class Success(
        val restaurants: List<Restaurant>
    ) : GroupRestaurantsViewState

    data class Error(
        val message: String,
        val restaurants: List<Restaurant>?
    ) : GroupRestaurantsViewState

    data class Loading(
        val restaurants: List<Restaurant>?
    ) : GroupRestaurantsViewState

    data object Empty : GroupRestaurantsViewState
}

