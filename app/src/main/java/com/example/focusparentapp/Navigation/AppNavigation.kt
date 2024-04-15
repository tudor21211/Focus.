package com.example.focusparentapp.Navigation

import android.content.Context
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.example.focusparentapp.Presentation.LandingScreen
import com.example.focusparentapp.Presentation.MainPage.MainPageScreen
import com.example.focusparentapp.Presentation.Tutorial.Setup
import com.example.focusparentapp.Presentation.Tutorial.TutorialPager
import com.example.focusparentapp.RoomDB.ViewModels.UsersViewModel

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SetupNavGraph(
    navController: NavHostController,
    context : Context,
    usersViewModel: UsersViewModel
) {
//change to LandingPage start destination
    AnimatedNavHost(
        navController = navController,
        startDestination = Screens.Setup.route
    ) {
        composable(Screens.LandingScreen.route) {
            LandingScreen(navController)
        }

        composable(Screens.MainPage.route) {
            MainPageScreen(navController, context, usersViewModel)
        }

        composable(Screens.TutorialPager.route){
            TutorialPager(navController)
        }

        composable(Screens.Setup.route){
            Setup(navController)
        }



    }
}