package com.haris.grouprestaurants.interactors

import com.haris.grouprestaurants.repositories.Repository
import javax.inject.Inject

class GetGroupRestaurantsInteractor @Inject constructor(
    private val repository: Repository
) {

    val flow = repository.data

    suspend operator fun invoke(id: String) {
        repository.getData(id)
    }
}