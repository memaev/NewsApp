package com.example.newsapp.di

import android.content.Context
import com.example.newsapp.data.repository.AuthRepository
import com.example.newsapp.data.repository.LocalAuthManager
import com.example.newsapp.data.repository.NewsRepository
import com.example.newsapp.domain.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideLocalAuthManager(@ApplicationContext context: Context): LocalAuthManager {
        return LocalAuthManager(context)
    }

    @Provides
    @Singleton
    fun provideAuthRepository(userDao: UserDao, localAuthManager: LocalAuthManager): AuthRepository {
        return AuthRepository(userDao, localAuthManager)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(): NewsRepository {
        return NewsRepository()
    }
}