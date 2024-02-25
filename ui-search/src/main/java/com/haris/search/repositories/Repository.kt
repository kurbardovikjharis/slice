package com.haris.search.repositories

import com.haris.data.Result
import com.haris.search.data.Group
import kotlinx.coroutines.flow.Flow

internal interface Repository {

    val data: Flow<Result<List<Group>>>

    suspend fun getData()
}