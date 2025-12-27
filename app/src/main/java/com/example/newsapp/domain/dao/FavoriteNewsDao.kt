package com.example.newsapp.domain.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.newsapp.domain.entity.FavoriteNewsItemEntity

@Dao
interface FavoriteNewsDao {
    @Insert
    suspend fun addFavoriteNewsItem(favoriteNewsItemEntity: FavoriteNewsItemEntity)

    @Query("DELETE FROM favorite_news WHERE id=:id")
    suspend fun deleteFavoriteNewsItemById(id: String)
}