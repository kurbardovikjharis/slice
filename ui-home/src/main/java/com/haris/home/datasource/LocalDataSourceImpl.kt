package com.haris.home.datasource

import com.haris.data.entities.Restaurant
import com.haris.data.restaurants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

internal class LocalDataSourceImpl : LocalDataSource {

    override suspend fun getData(): List<Restaurant> = withContext(Dispatchers.IO) {
        delay(2000) // simulate network call

        return@withContext restaurants
    }
}