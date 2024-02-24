package com.haris.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.haris.home.data.RestaurantEntity
import com.haris.home.data.Result
import com.haris.home.interactors.GetRestaurantsInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.annotation.concurrent.Immutable
import javax.inject.Inject

@HiltViewModel
internal class SensorsViewModel @Inject constructor(
    private val getRestaurantsInteractor: GetRestaurantsInteractor,
) : ViewModel() {

    init {
        viewModelScope.launch {
            getRestaurantsInteractor()
        }
    }

    val state: StateFlow<SensorsViewState> = getRestaurantsInteractor.flow.map {
        when (it) {
            is Result.Success -> {
                SensorsViewState.Success(
                    sensors = it.data ?: emptyList()
                )
            }

            is Result.Loading -> {
                SensorsViewState.Loading(
                    sensors = it.data
                )
            }

            is Result.Error -> {
                SensorsViewState.Error(
                    message = it.message ?: "",
                    sensors = it.data
                )
            }

            is Result.None -> SensorsViewState.Empty
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = SensorsViewState.Empty
    )

    fun retry() {
        viewModelScope.launch {
            getRestaurantsInteractor()
        }
    }
}

@Immutable
internal sealed interface SensorsViewState {

    data class Success(
        val sensors: List<RestaurantEntity>
    ) : SensorsViewState

    data class Error(
        val message: String, val sensors: List<RestaurantEntity>?
    ) : SensorsViewState

    data class Loading(
        val sensors: List<RestaurantEntity>?
    ) : SensorsViewState

    data object Empty : SensorsViewState
}

