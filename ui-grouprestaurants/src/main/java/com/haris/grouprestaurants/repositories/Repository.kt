package com.haris.grouprestaurants.repositories

import com.haris.data.entities.Group
import com.haris.data.entities.Result
import kotlinx.coroutines.flow.Flow

interface Repository {

    val data: Flow<Result<Group>>

    suspend fun getData(id: String)
}