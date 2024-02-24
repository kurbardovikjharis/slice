package com.haris.home.repositories

import com.haris.data.Result
import com.haris.home.data.Entity
import kotlinx.coroutines.flow.Flow

internal interface Repository {

    val data: Flow<Result<List<Entity>>>

    suspend fun getSensors()
}