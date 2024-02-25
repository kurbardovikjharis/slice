package com.haris.search.di

import com.haris.search.datasource.LocalDataSource
import com.haris.search.datasource.LocalDataSourceImpl
import com.haris.search.repositories.Repository
import com.haris.search.repositories.RepositoryImpl
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