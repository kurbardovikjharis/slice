package com.haris.search.datasource

import com.haris.data.entities.GroupEntity
import com.haris.data.entities.RestaurantEntity

interface LocalDataSource {

    suspend fun getData(): List<GroupEntity>

    suspend fun searchRestaurants(term: String): List<RestaurantEntity>
}