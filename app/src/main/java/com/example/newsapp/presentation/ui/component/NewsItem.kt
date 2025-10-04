package com.example.newsapp.presentation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.ArrowUpward
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.newsapp.domain.model.NewsItem
import com.example.newsapp.R
import com.example.newsapp.presentation.ui.theme.NewsAppTheme
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

@Composable
fun NewsItem(
    modifier: Modifier = Modifier,
    newsItem: NewsItem,
    onFavoriteClicked: () -> Unit,
    onReadClicked: () -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            AsyncImage(
                model = newsItem.imageUrl,
                contentDescription = "Фото новости",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp)),
                contentScale = ContentScale.Crop
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = newsItem.title,
                    fontSize = 22.sp
                )
                IconButton(
                    onClick = onFavoriteClicked
                ) {
                    Icon(
                        imageVector = if (newsItem.isFavorite) Icons.Filled.Favorite
                        else Icons.Outlined.Favorite,
                        contentDescription = "Добавить в избранные"
                    )
                }
            }
            Text(
                text = newsItem.description,
                maxLines = 3,
                fontSize = 18.sp
            )
            StyledButton(
                onClick = onReadClicked
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text(text = stringResource(R.string.read))

                    Icon(
                        imageVector = Icons.Outlined.ArrowUpward,
                        contentDescription = "Стрелка",
                        modifier = Modifier
                            .rotate(90f)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalTime::class)
@Preview
@Composable
fun NewsItemPreview() {
    NewsAppTheme {
        NewsItem(
            newsItem = NewsItem(
                id = "1",
                title = "News item 1",
                description = "News item 1 description",
                publishedBy = "News source",
                publishedAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
                imageUrl = "",
                isFavorite = true
            ),
            onFavoriteClicked = {},
            onReadClicked = {}
        )
    }
}