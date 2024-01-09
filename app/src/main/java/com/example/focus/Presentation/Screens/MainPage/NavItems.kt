package com.example.focus.Presentation.Screens.MainPage

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.focus.Presentation.Screens.Landing.Screen

data class NavItem(
    val label : String,
    val icon : ImageVector,
    val route : String
)

val listOfNavItems = listOf(
    NavItem(
        label = "Block",
        icon = Icons.Default.Home,
        route = ScreensMainPage.MainPageScreen.name
        ),
    NavItem(
        label = "Stats",
        icon = Icons.Default.DateRange,
        route = ScreensMainPage.StatsScreen.name
    ),
    NavItem (
        label = "Settings",
        icon = Icons.Default.Settings,
        route = ScreensMainPage.Settings.name
    )
)
