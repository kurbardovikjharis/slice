package com.haris.grouprestaurants.datasource

import com.haris.data.entities.GroupEntity
import com.haris.data.groups
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class LocalDataSourceImpl : LocalDataSource {

    override suspend fun getData(id: String): GroupEntity? = withContext(Dispatchers.IO) {
        delay(2000) // simulate network call

        return@withContext groups.find { it.id == id }
    }
}