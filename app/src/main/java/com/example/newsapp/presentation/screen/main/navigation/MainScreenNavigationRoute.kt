package com.example.newsapp.presentation.screen.main.navigation

import kotlinx.serialization.Serializable

interface MainScreenNavigationRoute {
    @Serializable
    data object Feed: MainScreenNavigationRoute

    @Serializable
    data object Profile: MainScreenNavigationRoute
}