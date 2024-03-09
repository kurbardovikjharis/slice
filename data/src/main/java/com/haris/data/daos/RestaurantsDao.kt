package com.haris.data.daos

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.haris.data.entities.RestaurantEntity

@Dao
interface RestaurantsDao {

    @Query("SELECT * FROM restaurantentity")
    fun pagingSource(): PagingSource<Int, RestaurantEntity>

    @Query("SELECT * FROM restaurantentity WHERE id IN (:restaurantIds)")
    fun loadAllByIds(restaurantIds: IntArray): List<RestaurantEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(users: List<RestaurantEntity>)
}
