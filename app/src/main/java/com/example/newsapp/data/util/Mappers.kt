package com.example.newsapp.data.util

import com.example.newsapp.data.dto.NewsItemDto
import com.example.newsapp.domain.model.NewsItem
import kotlin.time.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import java.security.MessageDigest
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
fun NewsItemDto.toModel(): NewsItem {
    return NewsItem(
        id = generateIdFromUrl(url),
        title = title ?: "No Title",
        url = url,
        description = description ?: "No Description",
        publishedBy = source?.name ?: "Unknown Source",
        publishedAt = publishedAt?.let { Instant.parse(it).toLocalDateTime(TimeZone.currentSystemDefault()) }
            ?: Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
        imageUrl = urlToImage ?: "",
        isFavorite = false
    )
}

private fun generateIdFromUrl(url: String?): String {
    return if (url != null) {
        MessageDigest.getInstance("MD5")
            .digest(url.toByteArray())
            .joinToString("") { "%02x".format(it) }
    } else {
        System.currentTimeMillis().toString()
    }
}