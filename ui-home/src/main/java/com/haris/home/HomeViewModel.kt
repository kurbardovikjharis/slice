package com.haris.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.haris.data.Restaurant
import com.haris.data.Result
import com.haris.domain.interactors.GetStreetNameInteractor
import com.haris.home.interactors.GetRestaurantsInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.annotation.concurrent.Immutable
import javax.inject.Inject

@HiltViewModel
internal class SensorsViewModel @Inject constructor(
    getStreetNameInteractor: GetStreetNameInteractor,
    private val getRestaurantsInteractor: GetRestaurantsInteractor,
) : ViewModel() {

    init {
        viewModelScope.launch {
            getRestaurantsInteractor()
        }
        viewModelScope.launch {
            getStreetNameInteractor()
        }
    }

    val state: StateFlow<SensorsViewState> =
        combine(
            getStreetNameInteractor.flow,
            getRestaurantsInteractor.flow
        ) { streetName, restaurantResult ->
            when (restaurantResult) {
                is Result.Success -> {
                    SensorsViewState.Success(
                        streetName = streetName,
                        restaurants = restaurantResult.data ?: emptyList()
                    )
                }

                is Result.Loading -> {
                    SensorsViewState.Loading(
                        streetName = streetName,
                        restaurants = restaurantResult.data
                    )
                }

                is Result.Error -> {
                    SensorsViewState.Error(
                        message = restaurantResult.message ?: "",
                        streetName = streetName,
                        restaurants = restaurantResult.data
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
internal sealed class SensorsViewState(
    open val streetName: String
) {

    data class Success(
        override val streetName: String,
        val restaurants: List<Restaurant>
    ) : SensorsViewState(streetName)

    data class Error(
        val message: String,
        override val streetName: String,
        val restaurants: List<Restaurant>?
    ) : SensorsViewState(streetName)

    data class Loading(
        override val streetName: String,
        val restaurants: List<Restaurant>?
    ) : SensorsViewState(streetName)

    data object Empty : SensorsViewState("")
}

