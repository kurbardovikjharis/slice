package com.haris.search.datasource

import com.haris.search.data.Group

interface LocalDataSource {

    suspend fun getData(): List<Group>
}