package com.haris.restaurantdetails.datasource

import com.haris.data.entities.MenuSubItemEntity
import com.haris.data.entities.RestaurantDetailsEntity

interface LocalDataSource {

    suspend fun getData(id: String): RestaurantDetailsEntity?
    suspend fun searchMenu(term: String): List<MenuSubItemEntity>
}