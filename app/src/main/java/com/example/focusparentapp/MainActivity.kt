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
import com.example.focusparentapp.RoomDB.ViewModels.PackageViewModel
import com.example.focusparentapp.RoomDB.ViewModels.UserViewModel
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private lateinit var packagesViewModel: PackageViewModel
    private lateinit var userViewModel: UserViewModel
    private lateinit var appDatabase: AppDatabase

    @OptIn(ExperimentalAnimationApi::class)
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appDatabase = AppDatabase.getDatabase(applicationContext)
        packagesViewModel = PackageViewModel(appDatabase.packagesDao())
        userViewModel = UserViewModel(appDatabase.userDao())
        userViewModel.insert(UserEntity("id1", "tudor.androne@yahoo.com"))
        packagesViewModel.insert(PackageEntity("com.example.focus", "Focus", "bytes", "id1"))
        packagesViewModel.insert(PackageEntity("com.example.focus1", "Focus1", "bytess", "id1"))
        packagesViewModel.insert(PackageEntity("com.example.focus2", "Focus2", "bytesss", "id1"))

        val userId = "id1"
        lifecycleScope.launch {
            userViewModel.getUserWithPackages(userId).observe(this@MainActivity) { userWithPackages ->
                println("User with packages: $userWithPackages")
            }
        }

        setContent {
            FocusParentAppTheme {
                // A surface container using the 'background' color from the theme
                val navController = rememberAnimatedNavController()
                SetupNavGraph(navController, this)

                //packagesViewModel.insert(PackageEntity("com.example.focus", "Focus", "bytes", userId))
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