package com.haris.search.repositories

import com.haris.data.entities.GroupEntity
import com.haris.data.entities.RestaurantEntity
import com.haris.data.entities.Result
import kotlinx.coroutines.flow.Flow

internal interface Repository {

    val data: Flow<Result<List<GroupEntity>>>
    val searchedRestaurants: Flow<List<RestaurantEntity>>

    suspend fun getData()
    suspend fun searchRestaurants(term: String)
}