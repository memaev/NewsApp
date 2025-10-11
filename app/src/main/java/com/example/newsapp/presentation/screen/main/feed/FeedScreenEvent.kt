package com.example.newsapp.presentation.screen.main.feed

import com.example.newsapp.domain.model.NewsItem

sealed interface FeedScreenEvent {
    data class SearchQueryChanged(val newSearchQuery: String): FeedScreenEvent
    data class NewsItemClicked(val newsItem: NewsItem): FeedScreenEvent
    data class NewsItemFavoriteToggleClicked(val newsItem: NewsItem): FeedScreenEvent
}