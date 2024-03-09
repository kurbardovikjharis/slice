package com.haris.home.repositories

import com.haris.home.datasource.LocalDataSource
import javax.inject.Inject

internal class RepositoryImpl @Inject constructor(
    private val dataSource: LocalDataSource,
) : Repository {

    override fun observeRestaurants() = dataSource.observeForPaging()
}
