package com.haris.domain.interactors

import com.haris.domain.repositories.UserRepository
import javax.inject.Inject

class GetStreetNameInteractor @Inject constructor(
    private val repository: UserRepository
) {

    val flow = repository.flow

    suspend operator fun invoke() {
        repository.getStreetName()
    }
}