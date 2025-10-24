package com.example.newsapp.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class NewsItemDto(
    val title: String?,
    val description: String?,
    val url: String?,
    val urlToImage: String?,
    val publishedAt: String?,
    val source: SourceDto?
)

@Serializable
data class SourceDto(
    val id: String?,
    val name: String?
)
