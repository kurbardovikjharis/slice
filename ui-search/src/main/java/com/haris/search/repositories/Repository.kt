package com.haris.search.repositories

import com.haris.data.entities.Group
import com.haris.data.entities.Restaurant
import com.haris.data.entities.Result
import kotlinx.coroutines.flow.Flow

internal interface Repository {

    val data: Flow<Result<List<Group>>>
    val searchedRestaurants: Flow<List<Restaurant>>

    suspend fun getData()
    suspend fun searchRestaurants(term: String)
}