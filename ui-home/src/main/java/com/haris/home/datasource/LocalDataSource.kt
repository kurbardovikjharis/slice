package com.haris.home.datasource

import com.haris.data.Restaurant

interface LocalDataSource {

    suspend fun getData(): List<Restaurant>
}