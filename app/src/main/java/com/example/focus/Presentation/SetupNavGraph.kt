package com.example.focus.Presentation

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable

@OptIn(ExperimentalAnimationApi::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SetupNavGraph(
    navController: NavHostController,
    context : Context
) {

    AnimatedNavHost(navController = navController, startDestination = Screen.LandingPage.route) {

        composable(route = Screen.LandingPage.route,
            exitTransition = {->
                slideOutHorizontally(
                    targetOffsetX = {-300},
                    animationSpec = tween(durationMillis = 150, easing = FastOutSlowInEasing)
                ) + fadeOut(animationSpec = tween(150))
            },
            popEnterTransition = {->
                slideInHorizontally (
                    initialOffsetX = {300},
                    animationSpec = tween(durationMillis = 150, easing = FastOutSlowInEasing)
                ) + fadeIn(animationSpec = tween(150))
            }

        ){
            landingPage(navController)
        }
        composable(route = Screen.AllAppsScreen.route,
            exitTransition = {->
                slideOutHorizontally(
                    targetOffsetX = {-300},
                    animationSpec = tween(durationMillis = 150, easing = FastOutSlowInEasing)
                ) + fadeOut(animationSpec = tween(150))
            },
            popEnterTransition = {->
                slideInHorizontally (
                    initialOffsetX = {300},
                    animationSpec = tween(durationMillis = 150, easing = FastOutSlowInEasing)
                ) + fadeIn(animationSpec = tween(150))
            }) {
            allAppsScreen(context , navController)
        }

        composable(route = Screen.TimeSpentScreen.route,
            exitTransition = {->
                slideOutHorizontally(
                    targetOffsetX = {-300},
                    animationSpec = tween(durationMillis = 150, easing = FastOutSlowInEasing)
                ) + fadeOut(animationSpec = tween(150))
            },
            popEnterTransition = {->
                slideInHorizontally (
                    initialOffsetX = {300},
                    animationSpec = tween(durationMillis = 150, easing = FastOutSlowInEasing)
                ) + fadeIn(animationSpec = tween(150))
            }
        ) {

            timeSpentScreen(context,0, navController)
        }

        composable (route = Screen.PermissionsScreen.route) {
            permissionScreen(navController)
        }

    }

}