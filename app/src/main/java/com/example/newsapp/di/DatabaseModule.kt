package com.example.newsapp.di

import android.content.Context
import androidx.room.Room
import com.example.newsapp.data.database.AppDatabase
import com.example.newsapp.domain.dao.FavoriteNewsDao
import com.example.newsapp.domain.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DaoModule {
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "appDatabase.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideUserDao(appDatabase: AppDatabase): UserDao {
        return appDatabase.getUserDao()
    }

    @Provides
    @Singleton
    fun provideFavoriteNewsDao(appDatabase: AppDatabase): FavoriteNewsDao {
        return appDatabase.getFavoriteNewsDao()
    }
}