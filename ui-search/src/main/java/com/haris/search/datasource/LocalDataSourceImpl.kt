package com.haris.search.datasource

import com.haris.data.entities.GroupEntity
import com.haris.data.entities.RestaurantEntity
import com.haris.data.groups
import com.haris.data.restaurants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

internal class LocalDataSourceImpl : LocalDataSource {

    override suspend fun getData(): List<GroupEntity> = withContext(Dispatchers.IO) {
        delay(2000) // simulate network call
        return@withContext groups
    }

    override suspend fun searchRestaurants(term: String): List<RestaurantEntity> {
        return restaurants.filter { it.name.contains(term, true) }
    }
}