package com.haris.home.datasource

import com.haris.home.data.RestaurantEntity

interface LocalDataSource {

    suspend fun getData(): List<RestaurantEntity>
}