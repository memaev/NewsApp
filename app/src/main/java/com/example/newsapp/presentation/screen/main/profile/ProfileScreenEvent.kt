package com.example.newsapp.presentation.screen.main.profile

import com.example.newsapp.domain.model.NewsItem

sealed interface ProfileScreenEvent {
    data class NewsItemClicked(val newsItem: NewsItem): ProfileScreenEvent
    data class NewsItemFavoriteToggleClicked(val newsItem: NewsItem): ProfileScreenEvent
}