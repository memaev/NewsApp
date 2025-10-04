package com.example.newsapp.presentation.screen.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.newsapp.presentation.navigation.Screen
import com.example.newsapp.presentation.screen.main.feed.FeedScreen
import com.example.newsapp.presentation.screen.main.profile.ProfileScreen
import com.example.newsapp.presentation.screen.main.navigation.BottomNavigationBar
import com.example.newsapp.presentation.screen.main.navigation.MainScreenNavigationRoute

@Composable
fun MainScreen(
    onNavigateTo: (Screen) -> Unit
) {
    val navController = rememberNavController()
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        bottomBar = { BottomNavigationBar(navController) }
    ) {
        NavHost(
            modifier = Modifier
                .padding(it),
            navController = navController,
            startDestination = MainScreenNavigationRoute.Feed
        ) {
            composable<MainScreenNavigationRoute.Feed> {
                FeedScreen()
            }
            composable<MainScreenNavigationRoute.Profile> {
                ProfileScreen()
            }
        }
    }
}