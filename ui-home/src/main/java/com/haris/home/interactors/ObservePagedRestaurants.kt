package com.haris.home.interactors

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.haris.data.entities.RestaurantEntity
import com.haris.domain.interactors.PagingInteractor
import com.haris.home.repositories.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObservePagedRestaurants @Inject constructor(
    private val repository: Repository
) : PagingInteractor<ObservePagedRestaurants.Parameters, RestaurantEntity>() {

    override fun createObservable(
        params: Parameters,
    ): Flow<PagingData<RestaurantEntity>> = Pager(config = params.pagingConfig) {
        repository.observeRestaurants()
    }.flow

    data class Parameters(
        override val pagingConfig: PagingConfig,
    ) : PagingInteractor.Parameters<RestaurantEntity>
}