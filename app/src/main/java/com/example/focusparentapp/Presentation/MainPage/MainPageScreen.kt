package com.example.focusparentapp.Presentation.MainPage

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.focusparentapp.Presentation.SharedViewModel
import com.example.focusparentapp.QRscan.QrScanner
import com.example.focusparentapp.R
import com.example.focusparentapp.RoomDB.Entities.UserEntity
import com.example.focusparentapp.RoomDB.ViewModels.UsersViewModel
import com.example.focusparentapp.WebSockets.WebSocketConnector
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.delay

@Composable
fun MainPageScreen(
    navController: NavController,
    context : Context,
    userViewModel: UsersViewModel,
    sharedViewModel: SharedViewModel
) {

    val systemUiController = rememberSystemUiController()
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    val isLoading by sharedViewModel.isLoading.collectAsState()

    SideEffect {
        systemUiController.setSystemBarsColor(Color(0xFF172238))
    }

    val colorStops = arrayOf(
        0.2f to Color(0xFF172238),
        0.5f to Color(0xFF121B2E),
        1f to Color(0xFF0A101E)
    )


    var users by remember { mutableStateOf<List<UserEntity>>(emptyList()) }

    var initialLoadCompleted by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        userViewModel.getAllUsers().collect { userList ->
            users = userList
            if(users.isEmpty()) {
                sharedViewModel.setLoadingState(true)
            }
            if (initialLoadCompleted) {
                Log.d("MainPageScreen", "Finished loading users")
                sharedViewModel.completeLoading()
            } else {
                initialLoadCompleted = true
            }
        }
    }
    LaunchedEffect(isLoading) {
        if (isLoading) {
            delay(20000)
            sharedViewModel.setLoadingState(false)
            Toast.makeText(context, "Connection timed-out", Toast.LENGTH_SHORT).show()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = Brush.linearGradient(colorStops = colorStops))
    ) {
        Column(
            modifier = Modifier

                .fillMaxSize()
                .background(brush = Brush.linearGradient(colorStops = colorStops))

        ) {


            Text(
                text = "My Family",
                modifier = Modifier
                    .padding(top = 10.dp, bottom = 50.dp)
                    .align(Alignment.CenterHorizontally),
                fontSize = 28.sp,
                color = Color.White,
                fontWeight = FontWeight(300),


                )

            Text(
                text = "Connected devices",
                fontWeight = FontWeight(600),
                modifier = Modifier.padding(start = 10.dp),
                fontSize = 20.sp,
                color = Color.White
            )
            LazyRow(
                modifier = Modifier

                    .fillMaxWidth()
            ) {
                val boyImages = listOf(R.drawable.boy2, R.drawable.boy3, R.drawable.boy4)
                val girlImages = listOf(R.drawable.girl2, R.drawable.girl3, R.drawable.girl)
                items(users.size + 1) { index ->
                    if (index > 0) {
                        val imageResId = if (index % 2 == 0) {
                            boyImages[(index / 2) % boyImages.size]
                        } else {
                            girlImages[(index / 2) % girlImages.size]
                        }

                        addButton(
                            painterResource = painterResource(id = imageResId),
                            onClick = {
                                WebSocketConnector.reconnectWebSocket(
                                    context,
                                    users[index - 1].userId
                                )
                                val webSocket = WebSocketConnector.getWebSocket()
                                webSocket?.send("HELLO THERE " + users[index - 1].userId)
                                webSocket?.send("${users[index - 1].userId} SEND_STATISTICS_TIME")
                                sharedViewModel.setUserDetails(index, imageResId)
                                navController.navigate("userMenu/${users[index - 1].userId}")
                            },
                            borderWidth = BorderStroke(1.dp, Color.White),
                            text = "Child $index",
                            addText = true
                        )
                    } else
                        addButton(
                            painterResource = painterResource(id = R.drawable.plussign),
                            onClick = {
                                sharedViewModel.setLoadingState(true)
                                val myIntent = Intent(
                                    context,
                                    QrScanner::class.java
                                )
                                (context as Activity).startActivityForResult(myIntent, 100)
                            },
                            borderWidth = BorderStroke(1.dp, Color.White),
                            text = "Add Profile",
                            addText = true
                        )
                }

            }

            Spacer(modifier = Modifier.fillMaxHeight(.2f))

            Text(
                text = "Activities",
                fontWeight = FontWeight(600),
                modifier = Modifier.padding(start = 20.dp),
                fontSize = 20.sp,
                color = Color.White
            )
            Spacer(modifier = Modifier.fillMaxHeight(.03f))

            activitiesSection()

        }

    }
    if (isLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF172238)),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator(color = Color.White)
                Text(
                    "Adding new profile...",
                    color = Color.White,
                    fontFamily = FontFamily(Font(R.font.opensans_res)),
                    fontSize = 25.sp
                )
            }

        }
    }

}



@Composable
fun addButton(painterResource : Painter, onClick: () -> Unit, borderWidth : BorderStroke, text : String, addText : Boolean){
    Column {
        Box(modifier = Modifier.padding(top = 10.dp, start = 5.dp, bottom = 5.dp, end = 10.dp)){
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .clickable(onClick = onClick)
                    .border(borderWidth, shape = CircleShape)
                ,
            ) {
                    Image(
                        painter = painterResource,
                        contentDescription = null,
                    )
            }

        }
        Text(
            text = text,
            fontSize = 12.sp,
            fontWeight = FontWeight(600),
            modifier = Modifier.align(Alignment.CenterHorizontally),
            color = Color.White
        )

    }

}


@Composable
fun activitiesSection(){
    val stroke = Stroke(
        width = 3f,
        pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
    )
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Box(
            modifier = Modifier
                .drawBehind {
                    drawRoundRect(
                        color = Color.White,
                        style = stroke,
                        cornerRadius = CornerRadius(20.dp.toPx())
                    )
                }
                .fillMaxWidth(.95f)
                .fillMaxHeight(.9f)
        ){
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text("No recent activity", color = Color.White, fontSize = 40.sp, fontFamily = FontFamily(Font(R.font.opensans_res)))
            }
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

