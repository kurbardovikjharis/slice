package com.haris.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.haris.data.Result
import com.haris.search.data.Group
import com.haris.search.data.Restaurant
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
    private val getGroupsInteractor: GetGroupsInteractor,
    private val searchRestaurantsInteractor: SearchRestaurantsInteractor
) : ViewModel() {

    private val termState = MutableStateFlow("")

    init {
        viewModelScope.launch {
            getGroupsInteractor()
        }
    }

    val state: StateFlow<SensorsViewState> =
        combine(
            termState,
            getGroupsInteractor.flow,
            searchRestaurantsInteractor.flow
        ) { term, groupsResult, searchedRestaurants ->
            when (groupsResult) {
                is Result.Success -> {
                    SensorsViewState.Success(
                        term = term,
                        groups = groupsResult.data ?: emptyList(),
                        searchedRestaurants = searchedRestaurants,
                    )
                }

                is Result.Loading -> {
                    SensorsViewState.Loading(
                        term = term,
                        groups = groupsResult.data,
                        searchedRestaurants = searchedRestaurants,
                    )
                }

                is Result.Error -> {
                    SensorsViewState.Error(
                        term = term,
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
internal sealed interface SensorsViewState {

    data class Success(
        val term: String,
        val groups: List<Group>,
        val searchedRestaurants: List<Restaurant>,
    ) : SensorsViewState

    data class Error(
        val message: String,
        val term: String,
        val groups: List<Group>?,
        val searchedRestaurants: List<Restaurant>,
    ) : SensorsViewState

    data class Loading(
        val term: String,
        val groups: List<Group>?,
        val searchedRestaurants: List<Restaurant>,
    ) : SensorsViewState

    data object Empty : SensorsViewState
}

