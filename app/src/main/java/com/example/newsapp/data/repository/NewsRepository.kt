package com.example.newsapp.data.repository

import com.example.newsapp.data.dto.NewsApiResponseDto
import com.example.newsapp.data.util.toModel
import com.example.newsapp.domain.model.NewsItem
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val httpClient: HttpClient
){
    suspend fun loadNews(): List<NewsItem> {
        return try {
            val response = httpClient.get("/top-headlines") {
                parameter("category", "technology")
            }.body<NewsApiResponseDto>()
            response.articles.map { it.toModel() }
        } catch (e: Exception) {
            emptyList()
        }
    }
}