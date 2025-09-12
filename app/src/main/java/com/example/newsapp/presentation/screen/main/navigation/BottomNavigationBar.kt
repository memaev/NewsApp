package com.example.newsapp.presentation.screen.main.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Create
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.newsapp.R
import com.example.newsapp.domain.util.BottomNavItem

@Composable
fun BottomNavigationBar(
    navController: NavController
) {
    val navigationItems = listOf(
        BottomNavItem(
            icon = Icons.Outlined.Home,
            titleResId = R.string.feed,
            route = MainScreenNavigationRoute.Feed
        ),
        BottomNavItem(
            icon = Icons.Outlined.Person,
            titleResId = R.string.profile,
            route = MainScreenNavigationRoute.Profile
        )
    )
    var selectedItemIndex by rememberSaveable { mutableIntStateOf(0) }
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.background
    ) {
        navigationItems.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedItemIndex == index,
                onClick = {
                    selectedItemIndex = index
                    navController.navigate(item.route)
                },
                colors =  NavigationBarItemDefaults.colors(
                    indicatorColor = Color.Transparent,
                    selectedIconColor = MaterialTheme.colorScheme.primary
                ),
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = null
                    )
                }
            )
        }
    }
}