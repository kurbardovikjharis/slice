package com.haris.restaurantdetails.datasource

import com.haris.data.entities.MenuSubItem
import com.haris.data.entities.RestaurantDetails

interface LocalDataSource {

    suspend fun getData(id: String): RestaurantDetails?
    suspend fun searchMenu(term: String): List<MenuSubItem>
}