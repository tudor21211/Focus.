package com.example.focusparentapp.Navigation

import android.content.Context
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.example.focusparentapp.Presentation.LandingScreen

@OptIn(androidx.compose.animation.ExperimentalAnimationApi::class)
@androidx.compose.runtime.Composable
fun SetupNavGraph(
    navController: androidx.navigation.NavHostController,
    context : android.content.Context
) {
//change to LandingPage start destination
    AnimatedNavHost(
        navController = navController,
        startDestination = Screens.LandingScreen.route
    ) {
        composable(Screens.LandingScreen.route) {
            LandingScreen(navController)
        }


    }
}