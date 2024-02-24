package com.haris.restaurantdetails.di

import com.haris.restaurantdetails.datasource.LocalDataSource
import com.haris.restaurantdetails.datasource.LocalDataSourceImpl
import com.haris.restaurantdetails.repositories.Repository
import com.haris.restaurantdetails.repositories.RepositoryImpl
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