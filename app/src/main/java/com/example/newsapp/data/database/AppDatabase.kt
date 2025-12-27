package com.example.newsapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.newsapp.domain.dao.FavoriteNewsDao
import com.example.newsapp.domain.dao.UserDao
import com.example.newsapp.domain.entity.FavoriteNewsItemEntity
import com.example.newsapp.domain.entity.User

@Database(entities = [User::class, FavoriteNewsItemEntity::class], version = 2, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun getUserDao(): UserDao
    abstract fun getFavoriteNewsDao(): FavoriteNewsDao
}