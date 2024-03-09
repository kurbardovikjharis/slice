package com.haris.grouprestaurants.repositories

import com.haris.data.entities.GroupEntity
import com.haris.data.entities.Result
import kotlinx.coroutines.flow.Flow

interface Repository {

    val data: Flow<Result<GroupEntity>>

    suspend fun getData(id: String)
}