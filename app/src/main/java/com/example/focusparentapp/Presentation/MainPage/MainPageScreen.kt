package com.example.focusparentapp.Presentation.MainPage

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.media.Image
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.focusparentapp.R
import com.example.focusparentapp.RoomDB.ViewModels.UsersViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalConfiguration
import com.example.focusparentapp.QRscan.QrScanner
import com.example.focusparentapp.RoomDB.Entities.UserEntity
import com.example.focusparentapp.WebSockets.WebSocketConnector
import com.example.focusparentapp.connectWebSocket
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking

@Composable
fun MainPageScreen(navController: NavController, context : Context, userViewModel: UsersViewModel){

    val systemUiController = rememberSystemUiController()
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    SideEffect {
        systemUiController.setSystemBarsColor(Color(0xFF6353F3))
        systemUiController.setNavigationBarColor(Color.Black)
    }
    val colorStops = arrayOf(
        0.2f to Color(0xFF6353F3),
        0.5f to Color(0xFF3C2EBD),
        1f to Color(0xFF190F6F)
    )


    var users by remember { mutableStateOf<List<UserEntity>>(emptyList()) }

    LaunchedEffect(Unit) {
        userViewModel.getAllUsers().collect { userList ->
            users = userList
        }
    }

    Row(

    ) {
        for (user in users) {
            addButton(
                painterResource = painterResource(id = R.drawable.boy) ,
                onClick = {
                    WebSocketConnector.reconnectWebSocket(context, user.userId)
                    val webSocket = WebSocketConnector.getWebSocket()
                    webSocket?.send("HELLO THERE "+user.userId)
                },
                borderWidth = BorderStroke(1.dp, Color.Black) )
        }
    }
    
}



@Composable
fun addButton(painterResource : Painter, onClick: () -> Unit, borderWidth : BorderStroke){
    Box(modifier = Modifier.padding(top = 10.dp, start = 15.dp)){
    Box(
        modifier = Modifier
            .size(100.dp) // Set a fixed size for the button
            .clip(CircleShape)
            .clickable(onClick = onClick)
            .border(borderWidth, shape = CircleShape)
        , // Fill the available space
        contentAlignment = Alignment.Center,
    ) {
        Image(
            painter = painterResource,
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.Center)
            ,
        )
     }
    }
}

@Composable
fun emptyCard(){
    Card(
        modifier = Modifier.fillMaxWidth(.9f),
        content = {}
    )
}