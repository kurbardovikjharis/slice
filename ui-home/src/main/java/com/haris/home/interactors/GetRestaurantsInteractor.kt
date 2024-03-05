package com.haris.home.interactors

import com.haris.home.repositories.Repository
import javax.inject.Inject

class GetRestaurantsInteractor @Inject constructor(
    private val repository: Repository
) {

    val flow = repository.data

    suspend operator fun invoke() {
        repository.getData()
    }
}