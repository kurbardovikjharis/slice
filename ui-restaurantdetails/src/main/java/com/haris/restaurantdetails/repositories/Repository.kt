package com.haris.restaurantdetails.repositories

import com.haris.data.Result
import com.haris.restaurantdetails.data.Entity
import kotlinx.coroutines.flow.Flow

internal interface Repository {

    val data: Flow<Result<Entity>>

    suspend fun getData(id: String)
}