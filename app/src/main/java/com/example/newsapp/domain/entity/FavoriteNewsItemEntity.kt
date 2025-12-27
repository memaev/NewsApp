package com.example.newsapp.domain.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@Entity(tableName = "favorite_news")
@OptIn(ExperimentalTime::class)
data class FavoriteNewsItemEntity(
    @PrimaryKey
    val id: String,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "url")
    val url: String?,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "published_by")
    val publishedBy: String,

    @ColumnInfo(name = "published_at")
    val publishedAt: Instant,

    @ColumnInfo(name = "image_url")
    val imageUrl: String,

    // id of the user that selected this NewsItem as favorite
    @ColumnInfo(name = "saved_by_user")
    val savedByUserId: String
)