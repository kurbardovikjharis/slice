package com.haris.home.datasource

import androidx.paging.PagingSource
import com.haris.data.entities.RestaurantEntity

interface LocalDataSource {

    fun observeForPaging(): PagingSource<Int, RestaurantEntity>
}