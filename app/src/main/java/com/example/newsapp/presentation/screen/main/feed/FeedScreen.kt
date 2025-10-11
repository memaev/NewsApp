package com.example.newsapp.presentation.screen.main.feed

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.newsapp.domain.model.NewsItem
import kotlinx.datetime.LocalDateTime
import com.example.newsapp.R
import com.example.newsapp.presentation.navigation.Screen

@Composable
fun FeedScreen(
    navigate: (Screen) -> Unit
) {
    val viewModel = hiltViewModel<FeedScreenViewModel, FeedScreenViewModel.Factory> {
        it.create(navigate)
    }
    val state by viewModel.state.collectAsStateWithLifecycle()

    FeedScreenContent(
        state = state,
        onEvent = viewModel::onEvent
    )
}

@Composable
fun FeedScreenContent(
    state: FeedScreenState,
    onEvent: (FeedScreenEvent) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = state.searchQuery,
            onValueChange = { onEvent(FeedScreenEvent.SearchQueryChanged(it)) },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Иконка поиска",
                    tint = Color.Gray
                )
            },
            placeholder = {
                Text(
                    text = stringResource(R.string.search_through_news),
                    fontSize = 18.sp
                )
            },
            shape = RoundedCornerShape(15.dp),
            modifier = Modifier.fillMaxWidth(0.9f)
        )
        Spacer(modifier = Modifier.height(10.dp))
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth(0.9f),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(state.filteredNews) {
                com.example.newsapp.presentation.ui.component.NewsItem(
                    newsItem = it,
                    onFavoriteClicked = {
                        onEvent(FeedScreenEvent.NewsItemFavoriteToggleClicked(it))
                    },
                    onReadClicked = {
                        onEvent(FeedScreenEvent.NewsItemClicked(it))
                    }
                )
            }
        }
    }
}