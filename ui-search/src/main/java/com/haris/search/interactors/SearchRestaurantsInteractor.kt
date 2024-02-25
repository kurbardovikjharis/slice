package com.haris.search.interactors

import com.haris.search.repositories.Repository
import javax.inject.Inject

internal class SearchRestaurantsInteractor @Inject constructor(
    private val repository: Repository
) {

    val flow = repository.searchedRestaurants

    suspend operator fun invoke(term: String) {
        repository.searchRestaurants(term)
    }
}