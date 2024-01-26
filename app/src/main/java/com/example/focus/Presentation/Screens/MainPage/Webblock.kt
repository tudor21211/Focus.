package com.example.focus.Presentation.Screens.MainPage

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
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.focus.Data.RestrictedAppsManager
import com.example.focus.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun settingsScreen() {

    val systemUiController = rememberSystemUiController()
    val colorStops = arrayOf(
        0.2f to Color(0xFF0E0653),
        0.5f to Color(0xFF090341),
        1f to Color(0xFF070231)
    )

    SideEffect {
        systemUiController.setSystemBarsColor(Color(0xFF0E0653))
    }


    Column(
        modifier = Modifier
            .background(Brush.linearGradient(colorStops = colorStops))
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.chrome),
            contentDescription = null,
            Modifier
                .fillMaxWidth(.4f)
                .padding(bottom = 10.dp)
        )
        Text(
            text = "Tame distractions by blocking specific websites and keywords that tend to steal your focus. It's like having a personal productivity coach that helps you stay on track and make the most of your online moments.",
            modifier = Modifier.fillMaxWidth(.9f),
            fontFamily = FontFamily(Font(R.font.opensans_res)),
            textAlign = TextAlign.Left,
            fontSize = 14.sp
        )
        Spacer(modifier = Modifier.fillMaxSize(.05f))
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 20.dp)
        ) {
            expandableCard("Block websites", "Enter website URL", "url")
            expandableCard("Block specific keywords", "Enter keyword", "keyword")
        }
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun expandableCard(title: String, textFieldLabel: String, type: String) {
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
                        fontFamily = FontFamily(Font(R.font.opensans_res))
                    )

                    if (expanded) {
                        Column {
                            textField(textFieldLabel, type) {
                                shouldUpdate = true
                            }
                            var restrictedUrls by remember { mutableStateOf(RestrictedAppsManager.getRestrictedUrl()) }
                            var restrictedKeywords by remember { mutableStateOf(RestrictedAppsManager.getRestrictedKeywords()) }

                            LaunchedEffect(shouldUpdate) {
                                if (shouldUpdate) {
                                    shouldUpdate = false
                                }
                            }

                            Column(
                                modifier = Modifier.padding(10.dp)
                            ) {
                                if (type == "url") restrictedUrls.forEach { url ->
                                    blockedWebsite(url,type) {
                                        shouldUpdate = true
                                    }
                                }
                                else restrictedKeywords.forEach { keyword ->
                                    blockedWebsite(keyword, type) {
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
                Icon(icon, "icon")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun textField(label: String, type: String, onEnterPressed: () -> Unit) {
    var text by remember { mutableStateOf("") }
    var toastText = ""
    if (type == "url") toastText = "URL added succesfully to blocklist!"
    else if (type == "keyword") toastText = "Keyword succesfully addded to blocklist!"
    val toast = Toast.makeText(LocalContext.current, toastText, Toast.LENGTH_LONG) // in Activity


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
                textColor = Color.White, // Text color
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
                    if (type == "url") RestrictedAppsManager.addRestrictedUrl(text.toLowerCase())
                    else if (type == "keyword") RestrictedAppsManager.addRestrictedKeyword(text.toLowerCase())
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
fun blockedWebsite(text : String, type : String, onDeletePressed: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(.93f).padding(bottom = 5.dp),
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
                    if (type == "url") {
                        RestrictedAppsManager.removeRestrictedUrl(text)
                        onDeletePressed()
                    }
                    else if (type == "keyword") {
                        RestrictedAppsManager.removeRestrictedKeyword(text)
                        onDeletePressed()
                    }
                }

            ) {
                Icon(imageVector = Icons.Default.Clear, contentDescription = null )
            }
        }
    }
}

