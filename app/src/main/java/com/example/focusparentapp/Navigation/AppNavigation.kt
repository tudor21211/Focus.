package com.example.focusparentapp.Navigation

import android.content.Context
import android.view.Window
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.focusparentapp.Presentation.Apps.AppsScreen
import com.example.focusparentapp.Presentation.DeviceBound
import com.example.focusparentapp.Presentation.DeviceUse.DeviceUse
//import com.google.accompanist.navigation.animation.AnimatedNavHost
//import com.google.accompanist.navigation.animation.composable
import com.example.focusparentapp.Presentation.LandingScreen
import com.example.focusparentapp.Presentation.Location.LocationScreen
import com.example.focusparentapp.Presentation.MainPage.MainPageScreen
import com.example.focusparentapp.Presentation.MainPage.TestScreen
import com.example.focusparentapp.Presentation.MainPage.UserMenu
import com.example.focusparentapp.Presentation.NetworkSettings.WebsitesScreen
import com.example.focusparentapp.Presentation.Restrictions.RestrictionsScreen
import com.example.focusparentapp.Presentation.Tutorial.Setup
import com.example.focusparentapp.Presentation.Tutorial.TutorialPager
import com.example.focusparentapp.RoomDB.ViewModels.UsersViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SetupNavGraph(
    navController: NavHostController,
    context : Context,
    usersViewModel: UsersViewModel,
) {
    val sharedPreferences = LocalContext.current.getSharedPreferences("TutorialFinished", Context.MODE_PRIVATE)
    val tutorialFinished = sharedPreferences.getBoolean("TutorialFinished", false)
    val firstQrScannedSharedPref = LocalContext.current.getSharedPreferences("FirstQrScanned", Context.MODE_PRIVATE)
    val firstQrScanned = firstQrScannedSharedPref.getBoolean("FirstQrScanned", false)
//change to LandingPage start destination
    NavHost(
        navController = navController,
        startDestination =
//        if (!tutorialFinished)
//            Screens.LandingScreen.route
//        else if(!firstQrScanned)
//            Screens.Setup.route
//        else
//            Screens.MainPage.route
    Screens.LocationScreen.route
    ) {
        composable(Screens.LandingScreen.route) {
            LandingScreen(navController)
        }

        composable(Screens.MainPage.route) {
            MainPageScreen(navController, context, usersViewModel)
        }

        composable(Screens.TutorialPager.route){
            TutorialPager(navController, sharedPreferences)
        }

        composable(Screens.Setup.route){
            Setup(navController, context)
        }

        composable(Screens.DeviceBound.route){
            DeviceBound(navController)
        }



        composable(
            Screens.UserMenu.route,
            arguments = listOf(navArgument("userId"){
                type = NavType.StringType
            })
        ){
            UserMenu(navController, it.arguments?.getString("userId")!!, usersViewModel)
        }

        composable(
            Screens.AppsScreen.route,
            arguments = listOf(navArgument("userId"){
                type = NavType.StringType
            })
        ){
            AppsScreen(navController, it.arguments?.getString("userId")!!, usersViewModel)
        }

        composable(
            Screens.RestrictionsScreen.route,
            arguments = listOf(navArgument("userId"){
                type = NavType.StringType
            })
        ){
            RestrictionsScreen(navController, it.arguments?.getString("userId")!!, usersViewModel)
        }


        composable(
            Screens.WebsitesScreen.route,
            arguments = listOf(navArgument("userId"){
                type = NavType.StringType
            })
        ){
            WebsitesScreen(navController, it.arguments?.getString("userId")!!, usersViewModel)
        }


        composable(
            Screens.DeviceUse.route,
            arguments = listOf(navArgument("userId"){
                type = NavType.StringType
            })
        ){
            DeviceUse(navController, it.arguments?.getString("userId")!!, usersViewModel)
        }

        composable(Screens.LocationScreen.route){
            LocationScreen(navController, context)
        }

        composable(Screens.TestScreen.route){
            TestScreen(navController)
        }
    }
}