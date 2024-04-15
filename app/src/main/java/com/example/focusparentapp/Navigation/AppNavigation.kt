package com.example.focusparentapp.Navigation

import android.content.Context
import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.example.focusparentapp.Presentation.LandingScreen
import com.example.focusparentapp.Presentation.MainPage.MainPageScreen
import com.example.focusparentapp.Presentation.Tutorial.Setup
import com.example.focusparentapp.Presentation.Tutorial.TutorialPager
import com.example.focusparentapp.QRscan.QrScanner
import com.example.focusparentapp.RoomDB.ViewModels.UsersViewModel

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SetupNavGraph(
    navController: NavHostController,
    context : Context,
    usersViewModel: UsersViewModel
) {
    val sharedPreferences = LocalContext.current.getSharedPreferences("TutorialFinished", Context.MODE_PRIVATE)
    val tutorialFinished = sharedPreferences.getBoolean("TutorialFinished", false)
    val firstQrScannedSharedPref = LocalContext.current.getSharedPreferences("FirstQrScanned", Context.MODE_PRIVATE)
    val firstQrScanned = firstQrScannedSharedPref.getBoolean("FirstQrScanned", false)
//change to LandingPage start destination
    AnimatedNavHost(
        navController = navController,
        startDestination =
        if (!tutorialFinished)
            Screens.LandingScreen.route
        else if(!firstQrScanned)
            Screens.Setup.route
        else
            Screens.MainPage.route
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



    }
}