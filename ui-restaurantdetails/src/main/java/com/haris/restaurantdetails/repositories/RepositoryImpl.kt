package com.haris.restaurantdetails.repositories

import com.haris.data.Result
import com.haris.restaurantdetails.data.Entity
import com.haris.restaurantdetails.datasource.LocalDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import timber.log.Timber
import javax.inject.Inject

internal class RepositoryImpl @Inject constructor(
    private val dataSource: LocalDataSource
) : Repository {

    private val _data: MutableStateFlow<Result<Entity>> =
        MutableStateFlow(Result.None())

    override val data: Flow<Result<Entity>>
        get() = _data

    override suspend fun getData(id: String) {
        val cachedData = _data.value.data
        _data.value = Result.Loading(cachedData)
        try {
            val data = dataSource.getData()
            if (data.name.isNotEmpty()) {
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
}
