package com.haris.domain.repositories

import kotlinx.coroutines.flow.Flow

interface UserRepository {

    val flow: Flow<String>

    suspend fun getStreetName()
}