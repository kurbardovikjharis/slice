package com.haris.restaurantdetails.repositories

import com.haris.data.Result
import com.haris.restaurantdetails.RestaurantDetailsEntity
import kotlinx.coroutines.flow.Flow

internal interface Repository {

    val data: Flow<Result<RestaurantDetailsEntity>>

    suspend fun getData(id: String)
}