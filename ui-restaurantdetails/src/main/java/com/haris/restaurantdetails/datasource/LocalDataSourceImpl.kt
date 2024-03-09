package com.haris.restaurantdetails.datasource

import com.haris.data.entities.MenuSubItemEntity
import com.haris.data.entities.RestaurantDetailsEntity
import com.haris.data.menuSubItems
import com.haris.data.restaurantsDetails
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

internal class LocalDataSourceImpl : LocalDataSource {

    override suspend fun getData(id: String): RestaurantDetailsEntity? = withContext(Dispatchers.IO) {
        delay(2000)
        return@withContext restaurantsDetails.find { it.id == id }
    }

    override suspend fun searchMenu(term: String): List<MenuSubItemEntity> = withContext(Dispatchers.IO) {
        return@withContext menuSubItems.filter { it.title.contains(term, true) }
    }
}