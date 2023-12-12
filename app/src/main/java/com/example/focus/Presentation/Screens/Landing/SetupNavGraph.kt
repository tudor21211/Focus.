package com.example.focus.Presentation.Screens.Landing

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.preference.PreferenceManager
import androidx.annotation.RequiresApi
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.example.focus.Model.Permissions.PermissionFunctions
import com.example.focus.Presentation.IndividualPermissions.accessibilityPermission
import com.example.focus.Presentation.IndividualPermissions.displayOverOtherAppsPermission
import com.example.focus.Presentation.IndividualPermissions.usageAccessPermission
import com.example.focus.Presentation.Screens.MainPage.mainPageScreen
import com.example.focus.Presentation.Screens.Quiz.quizScreen
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable

@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalAnimationApi::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SetupNavGraph(
    navController: NavHostController,
    context: Context
) {
    val sharedPreferences = LocalContext.current.getSharedPreferences("TutorialPermissionsFinished", Context.MODE_PRIVATE)
    val tutorialPermissionsFinished = sharedPreferences.getBoolean("TutorialPermissionsFinished", false)
    println("TutorialPermissionsFinished: $tutorialPermissionsFinished")
        AnimatedNavHost(navController = navController, startDestination =
        if (tutorialPermissionsFinished) Screen.QuizScreen.route else Screen.LandingPage.route){

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
        composable(route = Screen.MainPageScreen.route) {
            mainPageScreen()
        }
        composable(route = Screen.QuizScreen.route) {
            quizScreen()
        }
    }

}
