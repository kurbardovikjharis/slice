package com.haris.data.repositories

import com.haris.data.repositories.UserRepository
import com.haris.data.repositories.UserRepositoryImpl
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