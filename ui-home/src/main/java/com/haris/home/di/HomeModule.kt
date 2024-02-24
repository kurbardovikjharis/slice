package com.haris.home.di

import com.haris.home.datasource.LocalDataSource
import com.haris.home.datasource.LocalDataSourceImpl
import com.haris.home.repositories.HomeRepository
import com.haris.home.repositories.HomeRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
internal object HomeModule {

    @Provides
    fun provideDataSource(): LocalDataSource {
        return LocalDataSourceImpl()
    }

    @Provides
    fun provideRepository(dataSource: LocalDataSource): HomeRepository {
        return HomeRepositoryImpl(dataSource)
    }
}