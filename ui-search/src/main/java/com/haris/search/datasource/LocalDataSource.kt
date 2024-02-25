package com.haris.search.datasource

import com.haris.data.Group
import com.haris.data.Restaurant

interface LocalDataSource {

    suspend fun getData(): List<Group>

    suspend fun searchRestaurants(term: String): List<Restaurant>
}