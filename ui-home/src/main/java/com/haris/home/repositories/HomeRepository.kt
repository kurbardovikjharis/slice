package com.haris.home.repositories

import com.haris.home.data.RestaurantEntity
import com.haris.home.data.Result
import kotlinx.coroutines.flow.Flow

internal interface HomeRepository {

    val data: Flow<Result<List<RestaurantEntity>>>

    suspend fun getSensors()
}