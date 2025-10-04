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
import com.example.newsapp.domain.model.NewsItem
import kotlinx.datetime.LocalDateTime
import com.example.newsapp.R

@Composable
fun FeedScreen() {
    var searchText by remember { mutableStateOf("") }

    val sampleNewsItems = listOf(
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

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = searchText,
            onValueChange = { searchText = it },
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
            items(sampleNewsItems) {
                com.example.newsapp.presentation.ui.component.NewsItem(
                    newsItem = it,
                    onFavoriteClicked = {},
                    onReadClicked = {}
                )
            }
        }
    }
}