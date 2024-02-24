package com.haris.home.datasource

import com.haris.home.data.RestaurantEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

internal class LocalDataSourceImpl : LocalDataSource {

    override suspend fun getData(): List<RestaurantEntity> = withContext(Dispatchers.IO) {
        delay(2000) // simulate network call

        return@withContext listOf(
            RestaurantEntity("1", "Restaurant 1"),
            RestaurantEntity("2", "Restaurant 2"),
            RestaurantEntity("3", "Restaurant 3"),
            RestaurantEntity("4", "Restaurant 4"),
        )
    }
}