package com.example.focusparentapp

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.focusparentapp.Navigation.Screens
import com.example.focusparentapp.Navigation.SetupNavGraph
import com.example.focusparentapp.Presentation.SharedViewModel
import com.example.focusparentapp.RoomDB.ViewModels.UsersViewModel
import com.example.focusparentapp.WebSockets.WebSocketConnector
import com.example.focusparentapp.ui.theme.FocusParentAppTheme
import com.example.websocket.RoomDB.AppDatabase

class MainActivity : ComponentActivity() {

    private lateinit var userViewModel: UsersViewModel
    private lateinit var appDatabase: AppDatabase
    private lateinit var navController : NavHostController
    private val sharedViewModel: SharedViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appDatabase = AppDatabase.getDatabase(applicationContext)
        userViewModel = UsersViewModel(appDatabase.userDao())


        setContent {
            FocusParentAppTheme {
                navController = rememberNavController()
                SetupNavGraph(navController, this , userViewModel, sharedViewModel)

            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val sharedPreferences = this.getSharedPreferences("FirstQrScanned", MODE_PRIVATE)
        if(resultCode == 100) {
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