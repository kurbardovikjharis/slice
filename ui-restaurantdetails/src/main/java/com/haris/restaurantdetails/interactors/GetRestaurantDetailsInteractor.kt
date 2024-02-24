package com.haris.restaurantdetails.interactors

import com.haris.restaurantdetails.repositories.Repository
import javax.inject.Inject

internal class GetRestaurantDetailsInteractor @Inject constructor(
    private val repository: Repository
) {

    val flow = repository.data

    suspend operator fun invoke(id: String) {
        repository.getData(id)
    }
}