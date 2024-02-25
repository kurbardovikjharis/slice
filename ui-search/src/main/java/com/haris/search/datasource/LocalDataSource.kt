package com.haris.search.datasource

import com.haris.search.data.Group
import com.haris.search.data.Restaurant

interface LocalDataSource {

    suspend fun getData(): List<Group>

    suspend fun searchRestaurants(term: String): List<Restaurant>
}