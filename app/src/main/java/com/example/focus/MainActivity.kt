package com.example.focus

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.focus.Model.Permissions.PermissionFunctions
import com.example.focus.Presentation.Screen
import com.example.focus.Presentation.SetupNavGraph
import com.example.focus.Presentation.allAppsScreen
import com.example.focus.Presentation.landingPage
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

                if(requestCode==100) { // folosit pt a naviga inapoi la PermissionsScreen dupa ce am navigat in Settings si am bifat setarea
                    if (PermissionFunctions(this, getString(R.string.app_package_name)).isPackageUsageStatsPermissionEnabled()){
                    navController.navigate(Screen.PermissionsScreen.route) {
                        popUpTo(Screen.PermissionsScreen.route) {// dam remove din backstack la screen dupa ce am navigat inapoi la permissions screen
                            inclusive = true
                        }
                    }
                 }
                    else {
                        navController.navigate(Screen.UsageAccess.route) {
                            popUpTo(Screen.UsageAccess.route) {
                                inclusive = true
                            }
                        }
                    }
              }
                if (requestCode==200)
                { // folosit pt a naviga inapoi la PermissionsScreen dupa ce am navigat in Settings si am bifat setarea
                   if (PermissionFunctions(this, getString(R.string.app_package_name)).isAccessibilityServiceEnabled("MyAccessibilityService")) {
                       navController.navigate(Screen.PermissionsScreen.route) {
                           popUpTo(Screen.PermissionsScreen.route) {// dam remove din backstack la screen dupa ce am navigat inapoi la permissions screen
                               inclusive = true
                           }
                       }
                   }
                    else {
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







