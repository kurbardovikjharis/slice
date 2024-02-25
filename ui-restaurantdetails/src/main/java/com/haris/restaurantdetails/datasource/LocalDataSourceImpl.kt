package com.haris.restaurantdetails.datasource

import com.haris.data.RestaurantDetails
import com.haris.data.restaurantsDetails
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

internal class LocalDataSourceImpl : LocalDataSource {

    override suspend fun getData(id: String): RestaurantDetails? = withContext(Dispatchers.IO) {
        delay(2000)
        return@withContext restaurantsDetails.find { it.id == id }
    }
}