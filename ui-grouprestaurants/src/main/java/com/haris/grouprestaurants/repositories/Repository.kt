package com.haris.grouprestaurants.repositories

import com.haris.data.Restaurant
import com.haris.data.Result
import kotlinx.coroutines.flow.Flow

interface Repository {

    val data: Flow<Result<List<Restaurant>>>

    suspend fun getData(id: String)
}