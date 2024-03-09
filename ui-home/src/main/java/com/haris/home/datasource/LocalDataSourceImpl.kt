package com.haris.home.datasource

import androidx.paging.PagingSource
import com.haris.data.SliceDatabase
import com.haris.data.entities.RestaurantEntity
import javax.inject.Inject

internal class LocalDataSourceImpl @Inject constructor(
    private val database: SliceDatabase
) : LocalDataSource {

    override fun observeForPaging(): PagingSource<Int, RestaurantEntity> {
        return database.restaurantDao().pagingSource()
    }
}