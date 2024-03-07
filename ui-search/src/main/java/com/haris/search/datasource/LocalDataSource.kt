package com.haris.search.datasource

import com.haris.data.entities.Group
import com.haris.data.entities.Restaurant

interface LocalDataSource {

    suspend fun getData(): List<Group>

    suspend fun searchRestaurants(term: String): List<Restaurant>
}