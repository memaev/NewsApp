package com.example.newsapp.domain.model

import kotlinx.datetime.LocalDateTime

data class NewsItem(
    val id: String,
    val title: String,
    val description: String,
    val publishedBy: String,
    val publishedAt: LocalDateTime,
    val imageUrl: String,
    val isFavorite: Boolean
)
