package com.example.newsapp.domain.util

import androidx.compose.ui.graphics.vector.ImageVector
import com.example.newsapp.presentation.screen.main.navigation.MainScreenNavigationRoute

data class BottomNavItem(
    val icon: ImageVector,
    val titleResId: Int,
    val route: MainScreenNavigationRoute
)
