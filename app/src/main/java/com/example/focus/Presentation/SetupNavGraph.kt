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
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.focus.Presentation.IndividualPermissions.accessibilityPermission
import com.example.focus.Presentation.IndividualPermissions.displayOverOtherAppsPermission
import com.example.focus.Presentation.IndividualPermissions.usageAccessPermission
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable

@OptIn(ExperimentalAnimationApi::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SetupNavGraph(
    navController: NavHostController,
    context: Context
) {

    AnimatedNavHost(navController = navController, startDestination = Screen.LandingPage.route) {

        composable(route = Screen.LandingPage.route,
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { -100 },
                    animationSpec = tween(durationMillis = 100, easing = FastOutSlowInEasing)
                ) + fadeOut(animationSpec = tween(100))
            },
            popEnterTransition = {
                slideInHorizontally(
                    initialOffsetX = { 100 },
                    animationSpec = tween(durationMillis = 100, easing = FastOutSlowInEasing)
                ) + fadeIn(animationSpec = tween(100))
            }

        ) {
            landingPage(navController)
        }
        composable(route = Screen.AllAppsScreen.route,
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { -100 },
                    animationSpec = tween(durationMillis = 100, easing = FastOutSlowInEasing)
                ) + fadeOut(animationSpec = tween(100))
            },
            popEnterTransition = {
                slideInHorizontally(
                    initialOffsetX = { 100 },
                    animationSpec = tween(durationMillis = 100, easing = FastOutSlowInEasing)
                ) + fadeIn(animationSpec = tween(100))
            }) {
            allAppsScreen(context, navController)
        }

        composable(route = Screen.TimeSpentScreen.route,
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { -100 },
                    animationSpec = tween(durationMillis = 100, easing = FastOutSlowInEasing)
                ) + fadeOut(animationSpec = tween(100))
            },
            popEnterTransition = {
                slideInHorizontally(
                    initialOffsetX = { 100 },
                    animationSpec = tween(durationMillis = 100, easing = FastOutSlowInEasing)
                ) + fadeIn(animationSpec = tween(100))
            }
        ) {

            timeSpentScreen(context, 0, navController)
        }

        composable(route = Screen.PermissionsScreen.route) {
            permissionScreen(navController)
        }

        composable(route = Screen.UsageAccess.route) {
            usageAccessPermission()
        }

        composable(route = Screen.Accessibility.route) {
            accessibilityPermission()
        }

        composable(route = Screen.DisplayOverOtherApps.route) {
            displayOverOtherAppsPermission()
        }
    }

}