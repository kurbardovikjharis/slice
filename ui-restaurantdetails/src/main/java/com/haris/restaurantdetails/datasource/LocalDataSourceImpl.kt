package com.haris.restaurantdetails.datasource

import com.haris.restaurantdetails.data.Entity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

internal class LocalDataSourceImpl : LocalDataSource {

    override suspend fun getData(): Entity = withContext(Dispatchers.IO) {
        delay(2000)

        return@withContext Entity("")
    }
}