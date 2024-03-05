package com.haris.grouprestaurants.datasource

import com.haris.data.Restaurant

interface LocalDataSource {

    suspend fun getData(id: String): List<Restaurant>?
}