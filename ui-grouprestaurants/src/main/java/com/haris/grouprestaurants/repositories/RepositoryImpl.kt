package com.haris.grouprestaurants.repositories

import com.haris.data.entities.GroupEntity
import com.haris.data.entities.Result
import com.haris.grouprestaurants.datasource.LocalDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import timber.log.Timber
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val dataSource: LocalDataSource
) : Repository {

    private val _data: MutableStateFlow<Result<GroupEntity>> =
        MutableStateFlow(Result.None())

    override val data: Flow<Result<GroupEntity>>
        get() = _data

    override suspend fun getData(id: String) {
        val cachedData = _data.value.data
        _data.value = Result.Loading(cachedData)
        try {
            val data = dataSource.getData(id)
            if (data != null) {
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
