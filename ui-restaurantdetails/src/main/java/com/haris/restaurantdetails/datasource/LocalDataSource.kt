package com.haris.restaurantdetails.datasource

import com.haris.data.MenuSubItem
import com.haris.data.RestaurantDetails

interface LocalDataSource {

    suspend fun getData(id: String): RestaurantDetails?
    suspend fun searchMenu(term: String): List<MenuSubItem>
}