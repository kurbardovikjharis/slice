package com.haris.data

import com.haris.data.daos.RestaurantsDao

interface SliceDatabase {
    fun restaurantDao(): RestaurantsDao
}