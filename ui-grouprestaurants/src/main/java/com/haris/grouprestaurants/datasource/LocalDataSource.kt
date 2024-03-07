package com.haris.grouprestaurants.datasource

import com.haris.data.entities.Group

interface LocalDataSource {

    suspend fun getData(id: String): Group?
}