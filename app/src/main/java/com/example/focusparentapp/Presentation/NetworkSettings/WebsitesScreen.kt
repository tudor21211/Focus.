package com.example.focusparentapp.Presentation.NetworkSettings

import android.widget.Toast
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.focusparentapp.Presentation.Apps.TopBar
import com.example.focusparentapp.R
import com.example.focusparentapp.RoomDB.Entities.BlockedWebsiteEntity
import com.example.focusparentapp.RoomDB.Entities.RestrictedKeywordsEntity
import com.example.focusparentapp.RoomDB.ViewModels.UsersViewModel
import com.example.focusparentapp.WebSockets.WebSocketConnector
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONObject


@Composable
fun WebsitesScreen(navController: NavController, userId: String, usersViewModel: UsersViewModel){

    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setSystemBarsColor(Color(0xFF172238))
    }
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val colorStops = arrayOf(
        0.2f to Color(0xFF172238),
        0.5f to Color(0xFF121B2E),
        1f to Color(0xFF0A101E)
    )

    var myState1 by remember { mutableStateOf(false) }
    var myState2 by remember { mutableStateOf(false) }

    Column(modifier = Modifier
        .fillMaxSize()
        .background(brush = Brush.linearGradient(colorStops = colorStops))) {

        TopBar(navController, route = "userMenu/${userId}", "")

        expandableCard(title = "Block websites", textFieldLabel = "Enter a website URL", type = "url", usersViewModel, userId)
        expandableCard(title = "Block keywords", textFieldLabel = "Enter a keyword to filter", type = "keyword", usersViewModel, userId)

        Spacer(modifier = Modifier.weight(1f))

        SocialMediaBlockFeature("Block Instagram Reels", R.drawable.insta, myState1) { state ->
            myState1 = state
            sendStateChange(userId, "REELS", state)
        }
        SocialMediaBlockFeature("Block YouTube Shorts", R.drawable.yt, myState2) { state ->
            myState2 = state
            sendStateChange(userId, "SHORTS", state)
        }
        Spacer(modifier = Modifier.height(40.dp))
    }

}

fun sendStateChange(userId: String, feature: String, state: Boolean) {
    val action = if (state) "UPDATE_$feature" else "UPDATE_$feature"
    val jsonArray = JSONArray()
    val jsonObject = JSONObject().apply{
        put("$feature", state)
    }
    jsonArray.put(jsonObject)
    val finalJsonObject = JSONObject().apply {
        put("${userId}_$action", jsonArray)
    }
    val webSocket = WebSocketConnector.getWebSocket()
    webSocket?.send(finalJsonObject.toString())
}

@Composable
fun SocialMediaBlockFeature(title: String, iconId: Int, state: Boolean, onStateChange: (Boolean) -> Unit) {
    Image(
        painter = painterResource(id = iconId),
        contentDescription = null,
        modifier = Modifier.size(80.dp).padding(start = 20.dp)
    )
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(horizontal = 10.dp, vertical = 10.dp)) {

        Text(text = title, fontSize = 30.sp, modifier = Modifier.weight(1f).padding(start = 10.dp), color = Color.White, fontFamily = FontFamily(Font(R.font.opensans_res)))
        Checkbox(
            checked = state,
            onCheckedChange = { onStateChange(it) },
            colors = CheckboxDefaults.colors(
                uncheckedColor = Color.Red,
                checkedColor = Color.Green,
                checkmarkColor = Color.Black
            )
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun expandableCard(title: String, textFieldLabel: String, type: String, usersViewModel : UsersViewModel, userId : String) {
    var expanded by remember { mutableStateOf(false) }
    var shouldUpdate by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 15.dp, start = 25.dp, end = 25.dp)
            .clip(RoundedCornerShape(20.dp))
            .clickable {
                expanded = !expanded
            }
            .animateContentSize()
    ) {
        Card(
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(Color(0xff000000))
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = title,
                        modifier = Modifier.padding(start = 15.dp, top = 15.dp, bottom = 15.dp),
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily(Font(R.font.opensans_res)),
                        color = Color.White
                    )

                    if (expanded) {
                        Column {
                            textField(textFieldLabel, type, usersViewModel, userId) {
                                shouldUpdate = true
                            }

                            val restrictedUrls by usersViewModel.getBlockedWebsitesAsFlow(userId).collectAsState(initial = listOf())
                            val restrictedKeywords by usersViewModel.getRestrictedKeywordsAsFlow(userId).collectAsState(initial = listOf())


                            Column(
                                modifier = Modifier.padding(10.dp)
                            ) {
                                if (type == "url") restrictedUrls.forEach { url ->
                                    blockedWebsite(url,type, usersViewModel, userId) {
                                        shouldUpdate = true
                                    }
                                }
                                else restrictedKeywords.forEach { keyword ->
                                    blockedWebsite(keyword, type, usersViewModel, userId) {
                                        shouldUpdate = true
                                    }

                                }
                            }
                        }
                    }
                }
                val icon =
                    if (expanded) {
                        Icons.Default.KeyboardArrowUp
                    } else {
                        Icons.Default.KeyboardArrowDown
                    }
                Icon(icon, "icon", tint = Color.White)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun textField(label: String, type: String, usersViewModel: UsersViewModel, userId: String ,onEnterPressed: () -> Unit) {
    var text by remember { mutableStateOf("") }
    var toastText = ""
    if (type == "url") toastText = "URL added succesfully to blocklist!"
    else if (type == "keyword") toastText = "Keyword succesfully addded to blocklist!"
    val toast = Toast.makeText(LocalContext.current, toastText, Toast.LENGTH_LONG)


    Column(
        modifier = Modifier
            .padding(bottom = 16.dp, start = 10.dp)
    ) {
        val keyboardController = LocalSoftwareKeyboardController.current
        TextField(
            value = text,
            onValueChange = { newText -> text = newText },
            label = { Text(label, fontFamily = FontFamily(Font(R.font.opensans_res))) },
            trailingIcon = { Icon(Icons.Default.Warning, contentDescription = null) },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            maxLines = 1,
            modifier = Modifier.fillMaxWidth(.9f),
            colors = TextFieldDefaults.textFieldColors(
                focusedTextColor =  Color.White,
                containerColor = Color(0xFF000000),
                cursorColor = Color.Green,
                focusedIndicatorColor = Color(0xFF39E913),
                unfocusedIndicatorColor = Color.Yellow,
                disabledIndicatorColor = Color.Gray,
                focusedLabelColor = Color.White,
                unfocusedTrailingIconColor = Color(0xFFFD8C4F),
                focusedTrailingIconColor = Color(0xFF39E913)
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    if(type == "url") {
                        usersViewModel.viewModelScope.launch {
                            usersViewModel.insertBlockedWebsite(BlockedWebsiteEntity(text.toLowerCase(),userId))
                            sendRestrictWebsite(text.toLowerCase(),userId)
                        }
                    }
                   else if (type == "keyword") {
                        usersViewModel.viewModelScope.launch {
                            usersViewModel.insertRestrictedKeyword(RestrictedKeywordsEntity(text.toLowerCase(),userId))
                            sendRestrictKeyword(text.toLowerCase(),userId)
                        }
                   }
                    text = ""
                    onEnterPressed()
                    keyboardController?.hide()
                    toast.show()
                }

            )
        )

    }
}

@Composable
fun blockedWebsite(text : String, type : String, usersViewModel:UsersViewModel, userId: String, onDeletePressed: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth(.93f)
            .padding(bottom = 5.dp),
        colors = CardDefaults.cardColors(Color(0xFFEC8210))
    ) {
        Row(){
            Text(
                text = text,
                modifier = Modifier.padding(15.dp),
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily(Font(R.font.opensans_res))
            )
            Spacer(modifier = Modifier.fillMaxWidth(.8f))
            IconButton(
                onClick = {
                    if (type == "url"){
                        usersViewModel.viewModelScope.launch {
                            usersViewModel.removeBlockedWebsite(userId, text)
                            deleteBlockedWebsite(text, userId)
                            onDeletePressed()
                        }
                    }
                    else if (type == "keyword"){
                        usersViewModel.viewModelScope.launch {
                            usersViewModel.removeRestrictedKeyword(userId, text)
                            deleteRestrictedKeyword(text, userId)
                            onDeletePressed()
                        }
                    }

                }

            ) {
                Icon(imageVector = Icons.Default.Clear, contentDescription = null )
            }
        }
    }
}



fun sendRestrictWebsite(website : String , userId: String){
    val webSocket = WebSocketConnector.getWebSocket()
    val jsonArray = JSONArray()
    val jsonObject = JSONObject().apply{
        put("website", website)
        put("userId", userId)
    }
    jsonArray.put(jsonObject)
    val finalJsonObject = JSONObject().apply {
        put("${userId}_BLOCK_WEBSITE", jsonArray)
    }
    webSocket?.send(finalJsonObject.toString())
}

fun sendRestrictKeyword(keyword : String , userId: String){
    val webSocket = WebSocketConnector.getWebSocket()
    val jsonArray = JSONArray()
    val jsonObject = JSONObject().apply{
        put("keyword", keyword)
        put("userId", userId)
    }
    jsonArray.put(jsonObject)
    val finalJsonObject = JSONObject().apply {
        put("${userId}_BLOCK_KEYWORD", jsonArray)
    }
    webSocket?.send(finalJsonObject.toString())
}



fun deleteBlockedWebsite(website : String , userId: String){
    val webSocket = WebSocketConnector.getWebSocket()
    val jsonArray = JSONArray()
    val jsonObject = JSONObject().apply{
        put("website", website)
        put("userId", userId)
    }
    jsonArray.put(jsonObject)
    val finalJsonObject = JSONObject().apply {
        put("${userId}_REMOVE_WEBSITE", jsonArray)
    }
    webSocket?.send(finalJsonObject.toString())
}
fun deleteRestrictedKeyword(keyword : String , userId: String){
    val webSocket = WebSocketConnector.getWebSocket()
    val jsonArray = JSONArray()
    val jsonObject = JSONObject().apply{
        put("keyword", keyword)
        put("userId", userId)
    }
    jsonArray.put(jsonObject)
    val finalJsonObject = JSONObject().apply {
        put("${userId}_REMOVE_KEYWORD", jsonArray) // Add the array to a final JSON object
    }
    webSocket?.send(finalJsonObject.toString())
}
//TODO SEND DELETE RESTRICTED WEBSITE AND KEYWORD THROUGH WEBSOCKET