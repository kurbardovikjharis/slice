package com.haris.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.haris.data.entities.RestaurantEntity
import com.haris.domain.interactors.GetStreetNameInteractor
import com.haris.home.interactors.ObservePagedRestaurants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.annotation.concurrent.Immutable
import javax.inject.Inject

@HiltViewModel
internal class HomeViewModel @Inject constructor(
    getStreetNameInteractor: GetStreetNameInteractor,
    private val observePagedRestaurants: ObservePagedRestaurants,
) : ViewModel() {

    init {
        viewModelScope.launch {
            observePagedRestaurants(
                ObservePagedRestaurants.Parameters(
                    pagingConfig = PAGING_CONFIG,
                ),
            )
        }
        viewModelScope.launch {
            getStreetNameInteractor()
        }
    }

    val pagedList: Flow<PagingData<RestaurantEntity>> =
        observePagedRestaurants.flow.cachedIn(viewModelScope)

    val state: StateFlow<HomeViewState> = getStreetNameInteractor.flow.map {
        HomeViewState(it)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = HomeViewState()
    )

    companion object {
        private val PAGING_CONFIG = PagingConfig(
            pageSize = 4,
            initialLoadSize = 8,
        )
    }
}

@Immutable
internal data class HomeViewState(
    val streetName: String = ""
)