package com.haris.home.repositories

import com.haris.data.Restaurant
import com.haris.data.Result
import kotlinx.coroutines.flow.Flow

internal interface Repository {

    val data: Flow<Result<List<Restaurant>>>

    suspend fun getData()
}