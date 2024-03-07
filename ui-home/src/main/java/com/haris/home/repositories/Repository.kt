package com.haris.home.repositories

import com.haris.data.entities.Restaurant
import com.haris.data.entities.Result
import kotlinx.coroutines.flow.Flow

interface Repository {

    val data: Flow<Result<List<Restaurant>>>

    suspend fun getData()
}