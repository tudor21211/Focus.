package com.example.focusparentapp

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
import com.example.focusparentapp.Navigation.SetupNavGraph
import com.example.focusparentapp.RoomDB.Entities.PackageEntity
import com.example.focusparentapp.RoomDB.Entities.UserEntity
import com.example.focusparentapp.ui.theme.FocusParentAppTheme
import com.example.websocket.RoomDB.AppDatabase
import com.example.focusparentapp.RoomDB.ViewModels.UsersViewModel
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
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

        lifecycleScope.launch {

            val newUser1 = UserEntity(userId = "user1", email = "user1@example.com")
            val newUser2 = UserEntity(userId = "user2", email = "user2@example.com")
            val newPackages = listOf(
                PackageEntity(packageName = "package1", appName = "App1", icon = "..."),
                PackageEntity(packageName = "package3", appName = "App3", icon = "..."),
                PackageEntity(packageName = "package5", appName = "App5", icon = "...")
            )
            userViewModel.insertUserAndPackages(newUser1, newPackages)
            userViewModel.insertUserAndPackages(newUser2, newPackages)

        }

        setContent {
            FocusParentAppTheme {
                // A surface container using the 'background' color from the theme
                val navController = rememberAnimatedNavController()
                SetupNavGraph(navController, this)

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