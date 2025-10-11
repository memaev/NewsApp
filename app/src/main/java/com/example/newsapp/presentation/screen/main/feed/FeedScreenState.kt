package com.example.newsapp.presentation.screen.main.feed

import com.example.newsapp.domain.model.NewsItem

data class FeedScreenState(
    val searchQuery: String = "",
    val filteredNews: List<NewsItem> = emptyList()
)
