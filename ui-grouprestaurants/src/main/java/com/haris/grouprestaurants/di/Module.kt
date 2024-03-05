package com.haris.grouprestaurants.di

import com.haris.grouprestaurants.datasource.LocalDataSource
import com.haris.grouprestaurants.datasource.LocalDataSourceImpl
import com.haris.grouprestaurants.repositories.Repository
import com.haris.grouprestaurants.repositories.RepositoryImpl
import dagger.Provides

class Module {

    @Provides
    fun provideDataSource(): LocalDataSource {
        return LocalDataSourceImpl()
    }

    @Provides
    fun provideRepository(dataSource: LocalDataSource): Repository {
        return RepositoryImpl(dataSource)
    }
}