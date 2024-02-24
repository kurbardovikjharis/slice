package com.haris.restaurantdetails.datasource

import com.haris.restaurantdetails.data.Entity

interface LocalDataSource {

    suspend fun getData(): Entity
}