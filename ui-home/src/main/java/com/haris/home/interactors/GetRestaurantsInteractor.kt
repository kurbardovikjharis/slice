package com.haris.home.interactors

import com.haris.home.repositories.HomeRepository
import javax.inject.Inject

internal class GetRestaurantsInteractor @Inject constructor(
    private val repository: HomeRepository
) {

    val flow = repository.data

    suspend operator fun invoke() {
        repository.getSensors()
    }
}