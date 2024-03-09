package com.haris.restaurantdetails.repositories

import com.haris.data.entities.MenuSubItemEntity
import com.haris.data.entities.RestaurantDetailsEntity
import com.haris.data.entities.Result
import com.haris.restaurantdetails.RestaurantDetails
import com.haris.restaurantdetails.datasource.LocalDataSource
import com.haris.restaurantdetails.mapper.toRestaurantDetailsEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import timber.log.Timber
import javax.inject.Inject

internal class RepositoryImpl @Inject constructor(
    private val dataSource: LocalDataSource
) : Repository {

    private val _data: MutableStateFlow<Result<RestaurantDetails>> =
        MutableStateFlow(Result.None())
    private val _searchedMenu: MutableStateFlow<List<MenuSubItemEntity>> =
        MutableStateFlow(emptyList())

    override val data: Flow<Result<RestaurantDetails>>
        get() = _data
    override val searchedMenu: Flow<List<MenuSubItemEntity>>
        get() = _searchedMenu

    override suspend fun getData(id: String) {
        val cachedData = _data.value.data
        _data.value = Result.Loading(cachedData)
        try {
            val data = dataSource.getData(id)
            if (data != null) {
                _data.value = Result.Success(data.toRestaurantDetailsEntity())
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

    override suspend fun searchMenu(term: String) {
        _searchedMenu.value = dataSource.searchMenu(term)
    }
}
