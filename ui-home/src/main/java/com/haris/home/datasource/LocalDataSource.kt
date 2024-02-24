package com.haris.home.datasource

import com.haris.home.data.Entity

interface LocalDataSource {

    suspend fun getData(): List<Entity>
}