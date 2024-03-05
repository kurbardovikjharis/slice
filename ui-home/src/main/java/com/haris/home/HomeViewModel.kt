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

    val state: StateFlow<HomeViewState> =
        combine(
            getStreetNameInteractor.flow,
            getRestaurantsInteractor.flow
        ) { streetName, restaurantResult ->
            when (restaurantResult) {
                is Result.Success -> {
                    HomeViewState.Success(
                        streetName = streetName,
                        restaurants = restaurantResult.data ?: emptyList()
                    )
                }

                is Result.Loading -> {
                    HomeViewState.Loading(
                        streetName = streetName,
                        restaurants = restaurantResult.data
                    )
                }

                is Result.Error -> {
                    HomeViewState.Error(
                        message = restaurantResult.message ?: "",
                        streetName = streetName,
                        restaurants = restaurantResult.data
                    )
                }

                is Result.None -> HomeViewState.Empty
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = HomeViewState.Empty
        )

    fun retry() {
        viewModelScope.launch {
            getRestaurantsInteractor()
        }
    }
}

@Immutable
internal sealed class HomeViewState(
    open val streetName: String
) {

    data class Success(
        override val streetName: String,
        val restaurants: List<Restaurant>
    ) : HomeViewState(streetName)

    data class Error(
        val message: String,
        override val streetName: String,
        val restaurants: List<Restaurant>?
    ) : HomeViewState(streetName)

    data class Loading(
        override val streetName: String,
        val restaurants: List<Restaurant>?
    ) : HomeViewState(streetName)

    data object Empty : HomeViewState("")
}

