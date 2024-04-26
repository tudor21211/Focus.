package com.example.focusparentapp.Presentation.Restrictions

import android.graphics.drawable.Drawable
import android.graphics.drawable.Icon
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderColors
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.Popup
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.example.focusparentapp.Presentation.Apps.TopBar
import com.example.focusparentapp.Presentation.Apps.appDisplayCard
import com.example.focusparentapp.R
import com.example.focusparentapp.RoomDB.ViewModels.AppInfo
import com.example.focusparentapp.RoomDB.ViewModels.UsersViewModel
import com.example.focusparentapp.Utils.Utils
import com.example.focusparentapp.WebSockets.WebSocketConnector
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject

@Composable
fun RestrictionsScreen(navController: NavController, userId : String, usersViewModel: UsersViewModel){
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setSystemBarsColor(Color(0xFF172238))
    }
    val showDialog = remember { mutableStateOf(false) }
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val colorStops = arrayOf(
        0.2f to Color(0xFF172238),
        0.5f to Color(0xFF121B2E),
        1f to Color(0xFF0A101E)
    )

    var appsInfo by remember {
        mutableStateOf<List<AppInfo>>(emptyList())
    }

    var blockedApps by remember{
        mutableStateOf<List<String>>(emptyList())
    }

    LaunchedEffect(Unit) {
        withContext(Dispatchers.IO) {
            appsInfo = usersViewModel.getAppsInfoFromUser(userId)
            blockedApps = usersViewModel.getAllBlockedApps(userId)
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            //.border(1.dp, Color.Black)
            .background(brush = Brush.linearGradient(colorStops = colorStops))
            .then(if (showDialog.value) Modifier.blur(30.dp) else Modifier),
        horizontalAlignment = Alignment.Start
    ){
        TopBar(navController = navController, route = "userMenu/${userId}")
        LazyColumn(
            modifier = Modifier.padding(horizontal = 6.dp, vertical = 3.dp),
            content = {
                items(appsInfo.size)  { index->
                    val isLocked = remember{
                        mutableStateOf(
                            blockedApps.contains(appsInfo[index].packageName)
                        )
                    }
                    blockAppDisplayCard(appsInfo[index].appName, appsInfo[index].packageName, Utils().byteStringToDrawable(appsInfo[index].icon), showDialog, isLocked, userId, usersViewModel )
                }
            }
        )

    }

}


@Composable
fun blockAppDisplayCard(appName: String, appPackage: String, icon: Drawable, showDialog : MutableState<Boolean>, isLocked : MutableState<Boolean>, userId: String, usersViewModel: UsersViewModel){
    val isCardExpanded = remember { mutableStateOf(false) }


    Card(
        modifier = Modifier
            .padding(3.dp)
            .clickable { isCardExpanded.value = !isCardExpanded.value },
        colors =
        CardDefaults.outlinedCardColors(
            containerColor = Color(0xFF232B3F)
        ),
        shape = CardDefaults.elevatedShape,
        border = BorderStroke(1.dp, Color(0xFF101312))
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 14.dp)
        ){
            Image(
                painter = rememberAsyncImagePainter(model = icon),
                //painter = icon,
                contentDescription = null,
                modifier = Modifier
                    .size(80.dp)
                    .padding(end = 15.dp, top = 8.dp, bottom = 8.dp)
            )
            Column (
                modifier = Modifier.fillMaxWidth()
            ){
                Row (
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = appName,
                        color = Color.White,
                        fontSize = 20.sp,
                        fontFamily = FontFamily(Font(R.font.opensans_res)),

                        )

                        Text(text = "1 hr 30 min",
                            color = Color.White,
                            fontSize = 18.sp,
                            fontFamily = FontFamily(Font(R.font.opensans_res)),
                            modifier = Modifier.padding(end = 10.dp))



                }

                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween){
                    Text(
                        text = appPackage,
                        color = Color.Gray,
                        fontSize = 15.sp,
                        fontFamily = FontFamily(Font(R.font.opensans_res)),
                    )
                    if(!isCardExpanded.value){
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowDown,
                            contentDescription = null,
                            modifier = Modifier
                                .size(30.dp)
                                .padding(end = 10.dp),
                            tint = Color.White
                        )
                    }
                }

            }

        }
        if (isCardExpanded.value) {
            Spacer(modifier = Modifier.height(25.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                IconButton(onClick = {
                    showDialog.value = true
                }, modifier = Modifier.size(50.dp)) {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = null,
                        modifier = Modifier.size(45.dp),
                        tint = Color.White
                    )
                }
                IconButton(onClick = {

                    if(isLocked.value){
                       usersViewModel.viewModelScope.launch {
                           usersViewModel.updateIsBlocked(userId, appPackage, false)
                           sendRestrictMessage(appPackage, userId, 0)
                       }
                    }
                    else{
                        usersViewModel.viewModelScope.launch {
                            usersViewModel.updateIsBlocked(userId, appPackage, true)
                            sendRestrictMessage(appPackage, userId, 1)
                        }
                    }

                    isLocked.value = !isLocked.value


                }, modifier = Modifier.size(50.dp)) {
                    if (isLocked.value) {
                        Icon(
                            imageVector = Icons.Default.Lock,
                            contentDescription = null,
                            modifier = Modifier.size(45.dp),
                            tint = Color.Red
                        )
                    }
                    else {

                        Icon(
                            imageVector = Icons.Default.Lock,
                            contentDescription = null,
                            modifier = Modifier.size(45.dp),
                            tint = Color.Green
                        )
                    }
                }
            }
            if (showDialog.value) {
                Dialog(onDismissRequest = {
                    showDialog.value = false
                }) {
                    Box(
                        modifier =
                        Modifier
                            .size(300.dp, 300.dp)
                            .background(Color.Transparent)
                        ,
                        contentAlignment = Alignment.Center
                    ) {
                        val sliderPosition = remember { mutableStateOf(1f) }
                        Column {
                            Text(text = "Select restricted time", color = Color.White, fontFamily = FontFamily(Font(R.font.opensans_res)))
                            Slider(
                                value = sliderPosition.value,
                                onValueChange = { newValue ->
                                    sliderPosition.value = newValue
                                },
                                valueRange = 1f..24f,
                                steps = 24,
                                colors = SliderDefaults.colors(
                                    thumbColor = Color.Red,
                                    activeTrackColor = Color.Red
                                )
                            )
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(text = "${(sliderPosition.value).toInt() * 5} minutes", color = Color.White)
                                IconButton(onClick = {
                                    showDialog.value = !showDialog.value
                                }) {
                                    Icon(
                                        imageVector = Icons.Default.Check,
                                        contentDescription = null,
                                        modifier = Modifier.size(30.dp),
                                        tint = Color.Green
                                    )
                                }
                            }

                        }
                    }
                }
            }

        }
    }

}


fun sendRestrictMessage(appPackage : String , userId: String, code : Int){
    val webSocket = WebSocketConnector.getWebSocket()
    val jsonArray = JSONArray()
    val jsonObject =JSONObject().apply{
        put("packageName", appPackage)
        put("timeBlocked", code)
    }
    jsonArray.put(jsonObject)
    val finalJsonObject = JSONObject().apply {
        put("${userId}_BLOCK_PACKAGE", jsonArray) // Add the array to a final JSON object
    }
    webSocket?.send(finalJsonObject.toString())
}