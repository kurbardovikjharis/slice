package com.haris.search.datasource

import com.haris.data.Group
import com.haris.data.Restaurant
import com.haris.data.groups
import com.haris.data.restaurants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

internal class LocalDataSourceImpl : LocalDataSource {

    override suspend fun getData(): List<Group> = withContext(Dispatchers.IO) {
        delay(2000) // simulate network call
        return@withContext groups
    }

    override suspend fun searchRestaurants(term: String): List<Restaurant> {
        return restaurants.filter { it.name.contains(term) }
    }
}