package com.haris.grouprestaurants.datasource

import com.haris.data.entities.GroupEntity

interface LocalDataSource {

    suspend fun getData(id: String): GroupEntity?
}