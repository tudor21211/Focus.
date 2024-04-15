package com.example.focusparentapp

import android.content.Context
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
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.example.focusparentapp.Navigation.Screens
import com.example.focusparentapp.Navigation.SetupNavGraph
import com.example.focusparentapp.RoomDB.Entities.PackageEntity
import com.example.focusparentapp.RoomDB.Entities.UserEntity
import com.example.focusparentapp.ui.theme.FocusParentAppTheme
import com.example.websocket.RoomDB.AppDatabase
import com.example.focusparentapp.RoomDB.ViewModels.UsersViewModel
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private lateinit var userViewModel: UsersViewModel
    private lateinit var appDatabase: AppDatabase

    @OptIn(ExperimentalAnimationApi::class)
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appDatabase = AppDatabase.getDatabase(applicationContext)
        userViewModel = UsersViewModel(appDatabase.userDao())


        /*lifecycleScope.launch {

            val newUser1 = UserEntity(userId = "user1", email = "user1@example.com")
            val newUser2 = UserEntity(userId = "user2", email = "user2@example.com")
            val newPackages = listOf(
                PackageEntity(packageName = "package1", appName = "App1", icon = "..."),
                PackageEntity(packageName = "package3", appName = "App3", icon = "..."),
                PackageEntity(packageName = "package5", appName = "App5", icon = "..."),
                PackageEntity(packageName = "package7", appName = "App7", icon = "...")

            )
            userViewModel.insertUserAndPackages(newUser1, newPackages)
            userViewModel.insertUserAndPackages(newUser2, newPackages)

            userViewModel.getUserWithPackages("user1").observe(this@MainActivity) { userWithPackages ->
                println("User with packages: $userWithPackages")

            }

        }*/

        setContent {
            FocusParentAppTheme {
                // A surface container using the 'background' color from the theme
                val navController = rememberAnimatedNavController()
                SetupNavGraph(navController, this , userViewModel)

            }
        }
    }

    @OptIn(ExperimentalAnimationApi::class)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val sharedPreferences = this.getSharedPreferences("FirstQrScanned", MODE_PRIVATE)
        setContent{
            val navController = rememberAnimatedNavController()
            SetupNavGraph(navController, this , userViewModel)
            if(resultCode == 100) {
                val editor = sharedPreferences.edit()
                editor.putBoolean("FirstQrScanned", true)
                editor.apply()
                navController.navigate(Screens.MainPage.route) {
                    popUpTo(Screens.MainPage.route) {
                        inclusive = true
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
    FocusParentAppTheme {
        Greeting("Android")
    }
}