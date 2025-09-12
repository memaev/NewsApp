package com.example.newsapp.presentation.di

import com.example.newsapp.data.repository.AuthRepository
import com.example.newsapp.domain.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideAuthRepository(userDao: UserDao): AuthRepository {
        return AuthRepository(userDao)
    }
}