package com.haris.restaurantdetails.datasource

import com.haris.data.RestaurantDetails

interface LocalDataSource {

    suspend fun getData(id: String): RestaurantDetails?
}