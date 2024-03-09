package com.haris.search.repositories

import com.haris.data.entities.GroupEntity
import com.haris.data.entities.RestaurantEntity
import com.haris.data.entities.Result
import com.haris.search.datasource.LocalDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import timber.log.Timber
import javax.inject.Inject

internal class RepositoryImpl @Inject constructor(
    private val dataSource: LocalDataSource
) : Repository {

    private val _data: MutableStateFlow<Result<List<GroupEntity>>> =
        MutableStateFlow(Result.None())
    private val _searchedRestaurants: MutableStateFlow<List<RestaurantEntity>> =
        MutableStateFlow(emptyList())

    override val data: Flow<Result<List<GroupEntity>>>
        get() = _data
    override val searchedRestaurants: Flow<List<RestaurantEntity>>
        get() = _searchedRestaurants

    override suspend fun getData() {
        val cachedData = _data.value.data
        _data.value = Result.Loading(cachedData)
        try {
            val data = dataSource.getData()
            if (data.isNotEmpty()) {
                _data.value = Result.Success(data)
            } else {
                _data.value = Result.Error(
                    message = "no data",
                    data = cachedData
                )
            }
        } catch (exception: Exception) {
            Timber.e(exception)
            _data.value = Result.Error(
                message = exception.message,
                data = cachedData
            )
        }
    }

    override suspend fun searchRestaurants(term: String) {
        _searchedRestaurants.value = dataSource.searchRestaurants(term)
    }
}
