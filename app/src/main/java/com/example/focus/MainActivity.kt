package com.example.focus

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.preference.PreferenceManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import com.example.focus.Model.Permissions.PermissionFunctions
import com.example.focus.Presentation.Screens.Landing.Screen
import com.example.focus.Presentation.Screens.Landing.SetupNavGraph

import com.example.focus.ui.theme.FocusTheme
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalAnimationApi::class)
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            FocusTheme {
                val navController = rememberAnimatedNavController()
                SetupNavGraph(navController = navController, this)
            }
        }
    }


    @RequiresApi(Build.VERSION_CODES.O)
    @OptIn(ExperimentalAnimationApi::class)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        setContent {
            FocusTheme {
                val navController = rememberAnimatedNavController()
                SetupNavGraph(navController = navController, this)
                val sharedPreferences = this.getSharedPreferences("TutorialPermissionsFinished", MODE_PRIVATE)
                if (requestCode == 100) { // folosit pt a naviga inapoi la PermissionsScreen dupa ce am navigat in Settings si am bifat setarea
                    if (PermissionFunctions(this, packageName).areAllPermissionsEnabled()) {
                        val editor = sharedPreferences.edit()
                        editor.putBoolean("TutorialPermissionsFinished", true)
                        editor.apply() // Daca am terminat tutorialul de permisiuni ne ducem direct la quiz screen
                        navController.navigate(Screen.QuizScreen.route)
                    } else
                        if (PermissionFunctions(
                                this,
                                getString(R.string.app_package_name)
                            ).isPackageUsageStatsPermissionEnabled()
                        ) {
                            navController.navigate(Screen.PermissionsScreen.route) {
                                popUpTo(Screen.PermissionsScreen.route) {// dam remove din backstack la screen dupa ce am navigat inapoi la permissions screen
                                    inclusive = true
                                }
                            }
                        } else {
                            navController.navigate(Screen.UsageAccess.route) {
                                popUpTo(Screen.UsageAccess.route) {
                                    inclusive = true
                                }
                            }
                        }
                }
                if (requestCode == 200) { // folosit pt a naviga inapoi la PermissionsScreen dupa ce am navigat in Settings si am bifat setarea
                    if (PermissionFunctions(this, packageName).areAllPermissionsEnabled()) {
                        val editor = sharedPreferences.edit()
                        editor.putBoolean("TutorialPermissionsFinished", true)
                        editor.apply()
                        navController.navigate(Screen.QuizScreen.route)
                    } else
                        if (PermissionFunctions(
                                this,
                                getString(R.string.app_package_name)
                            ).isAccessibilityServiceEnabled("MyAccessibilityService")
                        ) {
                            navController.navigate(Screen.PermissionsScreen.route) {
                                popUpTo(Screen.PermissionsScreen.route) {// dam remove din backstack la screen dupa ce am navigat inapoi la permissions screen
                                    inclusive = true
                                }
                            }
                        } else {
                            navController.navigate(Screen.Accessibility.route) {
                                popUpTo(Screen.Accessibility.route) {
                                    inclusive = true
                                }
                            }
                        }

                }

                if (requestCode == 300) {
                    if (PermissionFunctions(this, packageName).areAllPermissionsEnabled()) {
                        val editor = sharedPreferences.edit()
                        editor.putBoolean("TutorialPermissionsFinished", true)
                        editor.apply()
                        navController.navigate(Screen.QuizScreen.route)
                    } else
                        if (PermissionFunctions(
                                this,
                                getString(R.string.app_package_name)
                            ).isOverlayPermissionEnabled()
                        ) {
                            navController.navigate(Screen.PermissionsScreen.route) {
                                popUpTo(Screen.PermissionsScreen.route) {// dam remove din backstack la screen dupa ce am navigat inapoi la permissions screen
                                    inclusive = true
                                }
                            }
                        } else {
                            navController.navigate(Screen.DisplayOverOtherApps.route) {
                                popUpTo(Screen.DisplayOverOtherApps.route) {
                                    inclusive = true
                                }
                            }
                        }

                }
            }
        }
    }
}




@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}











