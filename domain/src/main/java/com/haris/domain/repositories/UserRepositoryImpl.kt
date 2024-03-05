package com.haris.domain.repositories

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl : UserRepository {

    private val _streetName: MutableStateFlow<String> =
        MutableStateFlow("")

    override val flow: Flow<String>
        get() = _streetName

    override suspend fun getStreetName() {
        if (_streetName.value.isEmpty()) {
            _streetName.value = "13th Street"
        }
    }
}