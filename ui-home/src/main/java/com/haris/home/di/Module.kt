package com.haris.home.di

import com.haris.data.SliceDatabase
import com.haris.home.datasource.LocalDataSource
import com.haris.home.datasource.LocalDataSourceImpl
import com.haris.home.repositories.Repository
import com.haris.home.repositories.RepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
internal object Module {

    @Provides
    fun provideDataSource(database: SliceDatabase): LocalDataSource {
        return LocalDataSourceImpl(database)
    }

    @Provides
    fun provideRepository(dataSource: LocalDataSource): Repository {
        return RepositoryImpl(dataSource)
    }
}