package com.example.focus.Presentation

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.focus.MainActivity
import com.example.focus.Presentation.Screen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SetupNavGraph(
    navController: NavHostController,
    context : Context
) {

    NavHost(navController = navController, startDestination = Screen.AllAppsScreen.route) {

        composable(route = Screen.LandingPage.route) {
            landingPage()
        }
        composable(route = Screen.AllAppsScreen.route) {
            allAppsScreen(context , navController)
        }

        composable(route = Screen.TimeSpentScreen.route) {
            timeSpentScreen(context,0, navController)
        }

    }

}