package com.example.focus.Presentation.Screens.Landing

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.focus.Model.Permissions.PermissionFunctions

sealed class Screen(val route: String) {

    object AllAppsScreen : Screen(route = "allAppsScreen")
    object TimeSpentScreen : Screen(route = "timeSpentScreen")
    object LandingPage : Screen(route = "landingPage")
    object PermissionsScreen : Screen(route = "permissionsScreen")
    object Accessibility : Screen(route = "accessibilityPermission")
    object DisplayOverOtherApps : Screen(route = "displayOverOtherAppsPermission")
    object UsageAccess : Screen(route = "usageAccessPermission")
    object MainPageScreen : Screen(route = "mainPageScreen")
    object QuizScreen : Screen(route = "quizScreen")
    object QuizResponse : Screen(route = "quizResponse/{hours}")



}
