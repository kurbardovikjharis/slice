package com.haris.home.repositories

import androidx.paging.PagingSource
import com.haris.data.entities.RestaurantEntity

interface Repository {

    fun observeRestaurants(): PagingSource<Int, RestaurantEntity>
}