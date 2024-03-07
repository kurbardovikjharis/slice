package com.haris.home.datasource

import com.haris.data.entities.Restaurant

interface LocalDataSource {

    suspend fun getData(): List<Restaurant>
}