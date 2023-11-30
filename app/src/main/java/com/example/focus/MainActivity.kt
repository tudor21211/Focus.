package com.example.focus

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.focus.Model.Permissions.PermissionFunctions
import com.example.focus.Presentation.Screen
import com.example.focus.Presentation.SetupNavGraph
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

                if (requestCode == 100) { // folosit pt a naviga inapoi la PermissionsScreen dupa ce am navigat in Settings si am bifat setarea
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

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FocusTheme {
        Greeting("Android")
    }
}







