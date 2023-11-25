package com.example.focus.Presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.focus.Presentation.Screen

@Composable
fun SetupNavGraph(
    navController: NavHostController
) {

    NavHost(navController = navController, startDestination = Screen.LandingPage.route) {

        composable(route = Screen.LandingPage.route) {
            landingPage()
        }
        composable(route = Screen.AllAppsScreen.route) {
            allAppsScreen()
        }

        composable(route = Screen.TimeSpentScreen.route) {
            TimeSpentScreen()
        }

    }

}