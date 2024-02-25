package com.haris.search.interactors

import com.haris.search.repositories.Repository
import javax.inject.Inject

internal class GetGroupsInteractor @Inject constructor(
    private val repository: Repository
) {

    val flow = repository.data

    suspend operator fun invoke() {
        repository.getData()
    }
}