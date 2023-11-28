package com.example.focus.Presentation

sealed class Screen (val route : String) {

    object AllAppsScreen : Screen(route = "allAppsScreen")
    object TimeSpentScreen : Screen(route = "timeSpentScreen")
    object LandingPage : Screen(route = "landingPage")
    object PermissionsScreen : Screen(route = "permissionsScreen")

}
