package com.haris.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.haris.data.Group
import com.haris.data.Restaurant
import com.haris.data.Result
import com.haris.domain.interactors.GetStreetNameInteractor
import com.haris.search.interactors.GetGroupsInteractor
import com.haris.search.interactors.SearchRestaurantsInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
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
    private val getGroupsInteractor: GetGroupsInteractor,
    private val searchRestaurantsInteractor: SearchRestaurantsInteractor
) : ViewModel() {

    private val termState = MutableStateFlow("")

    init {
        viewModelScope.launch {
            getGroupsInteractor()
        }
        viewModelScope.launch {
            getStreetNameInteractor()
        }
    }

    val state: StateFlow<SensorsViewState> =
        combine(
            getStreetNameInteractor.flow,
            termState,
            getGroupsInteractor.flow,
            searchRestaurantsInteractor.flow
        ) { streetName, term, groupsResult, searchedRestaurants ->
            when (groupsResult) {
                is Result.Success -> {
                    SensorsViewState.Success(
                        streetName = streetName,
                        term = term,
                        groups = groupsResult.data ?: emptyList(),
                        searchedRestaurants = searchedRestaurants,
                    )
                }

                is Result.Loading -> {
                    SensorsViewState.Loading(
                        streetName = streetName,
                        term = term,
                        groups = groupsResult.data,
                        searchedRestaurants = searchedRestaurants,
                    )
                }

                is Result.Error -> {
                    SensorsViewState.Error(
                        term = term,
                        streetName = streetName,
                        message = groupsResult.message ?: "",
                        groups = groupsResult.data,
                        searchedRestaurants = searchedRestaurants,
                    )
                }

                is Result.None -> SensorsViewState.Empty
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = SensorsViewState.Empty
        )

    fun search(term: String) {
        termState.value = term
        viewModelScope.launch {
            searchRestaurantsInteractor(term)
        }
    }

    fun retry() {
        viewModelScope.launch {
            getGroupsInteractor()
        }
    }
}

@Immutable
internal sealed class SensorsViewState(
    open val streetName: String
) {

    data class Success(
        override val streetName: String,
        val term: String,
        val groups: List<Group>,
        val searchedRestaurants: List<Restaurant>,
    ) : SensorsViewState(streetName)

    data class Error(
        val message: String,
        override val streetName: String,
        val term: String,
        val groups: List<Group>?,
        val searchedRestaurants: List<Restaurant>,
    ) : SensorsViewState(streetName)

    data class Loading(
        val term: String,
        override val streetName: String,
        val groups: List<Group>?,
        val searchedRestaurants: List<Restaurant>,
    ) : SensorsViewState(streetName)

    data object Empty : SensorsViewState("")
}

