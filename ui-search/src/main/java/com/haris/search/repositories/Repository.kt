package com.haris.search.repositories

import com.haris.data.Group
import com.haris.data.Restaurant
import com.haris.data.Result
import kotlinx.coroutines.flow.Flow

internal interface Repository {

    val data: Flow<Result<List<Group>>>
    val searchedRestaurants: Flow<List<Restaurant>>

    suspend fun getData()
    suspend fun searchRestaurants(term: String)
}