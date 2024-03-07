package com.haris.restaurantdetails.repositories

import com.haris.data.entities.MenuSubItem
import com.haris.data.entities.Result
import com.haris.restaurantdetails.RestaurantDetailsEntity
import kotlinx.coroutines.flow.Flow

internal interface Repository {

    val data: Flow<Result<RestaurantDetailsEntity>>
    val searchedMenu: Flow<List<MenuSubItem>>

    suspend fun getData(id: String)
    suspend fun searchMenu(term: String)
}