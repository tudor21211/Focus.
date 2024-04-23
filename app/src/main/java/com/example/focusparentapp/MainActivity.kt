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
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.findNavController
import com.example.focuschildapp.com.example.focuschildapp.WebSockets.WebSocketManager
import com.example.focusparentapp.Navigation.Screens
import com.example.focusparentapp.Navigation.SetupNavGraph
import com.example.focusparentapp.RoomDB.Entities.PackageEntity
import com.example.focusparentapp.RoomDB.Entities.UserEntity
import com.example.focusparentapp.ui.theme.FocusParentAppTheme
import com.example.websocket.RoomDB.AppDatabase
import com.example.focusparentapp.RoomDB.ViewModels.UsersViewModel
import com.example.focusparentapp.WebSockets.WebSocketConnector
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request

class MainActivity : ComponentActivity() {

    private lateinit var userViewModel: UsersViewModel
    private lateinit var appDatabase: AppDatabase
    private lateinit var navController : NavHostController
    @OptIn(ExperimentalAnimationApi::class)
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appDatabase = AppDatabase.getDatabase(applicationContext)
        userViewModel = UsersViewModel(appDatabase.userDao())


        setContent {
            FocusParentAppTheme {
                // A surface container using the 'background' color from the theme
                navController = rememberAnimatedNavController()
                SetupNavGraph(navController, this , userViewModel)

            }
        }
    }

    @OptIn(ExperimentalAnimationApi::class)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val sharedPreferences = this.getSharedPreferences("FirstQrScanned", MODE_PRIVATE)
            if(resultCode == 100) {
//                navController = rememberAnimatedNavController()
//                SetupNavGraph(navController, this , userViewModel)
                val editor = sharedPreferences.edit()
                editor.putBoolean("FirstQrScanned", true)
                editor.apply()
                val endPoint = data?.getStringExtra("result")
                connectWebSocket(this, endPoint!!)
                navController.navigate(Screens.MainPage.route) {
                    popUpTo(Screens.MainPage.route) {
                        inclusive = true
                    }
                }
            }

    }

}

fun connectWebSocket(context: Context, endPoint : String) {
//    val client = OkHttpClient()
//    val request = Request.Builder().url("ws://192.168.0.112:8200/ws/$endPoint").build()
//    val listener = WebSocketManager(context)
//    val webSocket = client.newWebSocket(request, listener)
//    //TODO send first time information only if the user is new
//    webSocket.send("$endPoint SEND_FIRST_TIME_APPS_DETAILS")
//    //webSocket.close(1001, "Closing the connection!")
    WebSocketConnector.connectWebSocket(context, endPoint)
    val webSocket = WebSocketConnector.getWebSocket()
    webSocket?.send("$endPoint SEND_FIRST_TIME_APPS_DETAILS")
}