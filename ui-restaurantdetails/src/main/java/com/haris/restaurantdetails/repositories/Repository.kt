package com.haris.restaurantdetails.repositories

import com.haris.data.entities.MenuSubItemEntity
import com.haris.data.entities.Result
import com.haris.restaurantdetails.RestaurantDetails
import kotlinx.coroutines.flow.Flow

internal interface Repository {

    val data: Flow<Result<RestaurantDetails>>
    val searchedMenu: Flow<List<MenuSubItemEntity>>

    suspend fun getData(id: String)
    suspend fun searchMenu(term: String)
}