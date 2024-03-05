package com.haris.grouprestaurants.datasource

import com.haris.data.Group

interface LocalDataSource {

    suspend fun getData(id: String): Group?
}