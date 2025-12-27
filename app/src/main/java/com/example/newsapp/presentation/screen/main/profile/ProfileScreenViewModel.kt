package com.example.newsapp.presentation.screen.main.profile

import androidx.lifecycle.ViewModel
import com.example.newsapp.data.repository.FavoriteNewsRepository
import com.example.newsapp.domain.model.NewsItem
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

@HiltViewModel
class ProfileScreenViewModel @Inject constructor(
    private val favoriteNewsRepository: FavoriteNewsRepository
): ViewModel() {
    private val _state = MutableStateFlow(ProfileScreenState())
    val state = _state.asStateFlow()

    fun onEvent(event: ProfileScreenEvent) {
        when(event) {
            is ProfileScreenEvent.NewsItemClicked -> onNewsItemClicked(event.newsItem)
            is ProfileScreenEvent.NewsItemFavoriteToggleClicked -> onNewsItemFavoriteToggleClicked(event.newsItem)
        }
    }

    private fun onNewsItemClicked(newsItem: NewsItem) {
        _state.update { it.copy(selectedNewsArticleUrl = newsItem.url) }
    }

    private fun onNewsItemFavoriteToggleClicked(newsItem: NewsItem) {
        if (newsItem.isFavorite) {
            val updatedFavoriteNews = state.value.favoriteNews?.filter { it.id != newsItem.id }
            _state.update { it.copy(favoriteNews = updatedFavoriteNews) }
        }
    }

}