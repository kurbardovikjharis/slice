package com.haris.domain.di

import com.haris.domain.repositories.UserRepository
import com.haris.domain.repositories.UserRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
internal object Module {

    @Singleton
    @Provides
    fun provideRepository(): UserRepository {
        return UserRepositoryImpl()
    }
}