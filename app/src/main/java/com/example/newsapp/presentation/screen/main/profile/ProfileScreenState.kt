package com.example.newsapp.presentation.screen.main.profile

import com.example.newsapp.domain.entity.User
import com.example.newsapp.domain.model.NewsItem

data class ProfileScreenState(
    val currentUser: User? = null,
    val favoriteNews: List<NewsItem>? = null,
    val selectedNewsArticleUrl: String? = null
)
