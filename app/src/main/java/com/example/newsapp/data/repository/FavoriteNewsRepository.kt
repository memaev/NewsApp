package com.example.newsapp.data.repository

import com.example.newsapp.data.util.toFavoriteNewsItemEntity
import com.example.newsapp.domain.dao.FavoriteNewsDao
import com.example.newsapp.domain.model.NewsItem
import javax.inject.Inject

class FavoriteNewsRepository @Inject constructor(
    private val favoriteNewsDao: FavoriteNewsDao
){
    suspend fun addNewsItemToFavorite(newsItem: NewsItem, currentUserId: String) {
        val favoriteNewsItemEntity = newsItem.toFavoriteNewsItemEntity(savedByUserId = currentUserId)
        favoriteNewsDao.addFavoriteNewsItem(favoriteNewsItemEntity)
    }
}