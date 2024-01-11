package com.example.focus.Presentation.Screens.MainPage

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.focus.Presentation.Screens.Landing.Screen
import com.example.focus.Presentation.Screens.Landing.allAppsScreen
import com.example.focus.Presentation.Screens.Landing.timeSpentScreen

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation() {

    val navController : NavHostController = rememberNavController()

    Scaffold(

        bottomBar =  {
            NavigationBar (
                containerColor = Color.Black,

            ) {
                val navBackstackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackstackEntry?.destination

                listOfNavItems.forEach {navItem ->
                    NavigationBarItem(
                        selected = currentDestination?.hierarchy?.any { it.route == navItem.route } == true,
                        onClick = {
                                  navController.navigate(navItem.route) {
                                      popUpTo(navController.graph.findStartDestination().id) {
                                          saveState = true
                                      }
                                      launchSingleTop = true
                                      restoreState = true
                                  }
                        },
                        icon = {
                            Icon(imageVector = navItem.icon, contentDescription = null) },
                        label = {
                            Text(text = navItem.label)
                        })
                }
            }
        }


    ) { paddingValues ->
        NavHost(navController = navController,
            startDestination = ScreensMainPage.MainPageScreen.name,
            modifier = Modifier.padding(paddingValues))
        {
            composable(
                route = ScreensMainPage.MainPageScreen.name
            ) {
                mainPageScreen(navController)
            }

            composable(
                route = ScreensMainPage.Settings.name
            ) {
                settingsScreen()
            }
            composable(
                route = ScreensMainPage.AllAppsScreen.name
            ){
                allAppsScreen(context = LocalContext.current, navController = navController )
            }

            composable (
                route = ScreensMainPage.TimeSpentScreen.name
            ) {
                timeSpentScreen(context = LocalContext.current, timeInterval = 1 , navController = navController )
            }

            composable(
                route = ScreensMainPage.TimeConsumingApps.name
            ) {
                timeConsumingApps()
            }
        }
    }


}