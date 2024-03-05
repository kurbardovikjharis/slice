package com.haris.grouprestaurants.di

import com.haris.grouprestaurants.datasource.LocalDataSource
import com.haris.grouprestaurants.datasource.LocalDataSourceImpl
import com.haris.grouprestaurants.repositories.Repository
import com.haris.grouprestaurants.repositories.RepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
internal object Module {

    @Provides
    fun provideDataSource(): LocalDataSource {
        return LocalDataSourceImpl()
    }

    @Provides
    fun provideRepository(dataSource: LocalDataSource): Repository {
        return RepositoryImpl(dataSource)
    }
}