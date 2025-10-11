package com.example.newsapp.data.repository

import com.example.newsapp.domain.model.NewsItem
import kotlinx.datetime.LocalDateTime

class NewsRepository {
    suspend fun loadNews(): List<NewsItem> {
        return listOf(
            NewsItem(
                id = "1",
                title = "Breaking News: Compose Simplifies UI Development",
                description = "Jetpack Compose is revolutionizing Android UI development with its declarative approach.",
                imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTuNlsfnHCpJNqy_3bE_Qk1K3HWiUkggy8z8g&s",
                isFavorite = false,
                publishedBy = "Tech News Daily",
                publishedAt = LocalDateTime(2024, 6, 15, 10, 0)
            ),
            NewsItem(
                id = "2",
                title = "Kotlin Multiplatform: One Language, Many Platforms",
                description = "Kotlin Multiplatform allows developers to share code across multiple platforms seamlessly.",
                imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTuNlsfnHCpJNqy_3bE_Qk1K3HWiUkggy8z8g&s",
                isFavorite = true,
                publishedBy = "Dev Weekly",
                publishedAt = LocalDateTime(2024, 6, 14, 9, 30)
            ),
            NewsItem(
                id = "3",
                title = "Android 14: What's New?",
                description = "Explore the latest features and improvements in Android 14.",
                imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTuNlsfnHCpJNqy_3bE_Qk1K3HWiUkggy8z8g&s",
                isFavorite = false,
                publishedBy = "Android Central",
                publishedAt = LocalDateTime(2024, 6, 13, 14, 15)
            )
        )
    }
}