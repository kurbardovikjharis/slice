package com.haris.restaurantdetails.interactors

import com.haris.restaurantdetails.repositories.Repository
import javax.inject.Inject

internal class SearchMenuInteractor @Inject constructor(
    private val repository: Repository
) {

    val flow = repository.searchedMenu

    suspend operator fun invoke(term: String) {
        repository.searchMenu(term)
    }
}