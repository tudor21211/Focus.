package com.example.focus.Presentation.Screens.MainPage

import android.text.Editable
import android.widget.EditText
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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import androidx.compose.material3.Icon
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.unit.TextUnit
import com.example.focus.Data.RestrictedAppsManager

@Composable

fun settingsScreen () {

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
        verticalArrangement =  Arrangement.Center) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(top = 20.dp)) {
            expandableCard("Block websites", "Enter website URL", "url")
            expandableCard("Block specific keywords", "Enter keyword", "keyword")
        }
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun expandableCard(title: String, textFieldLabel : String, type : String) {
    var expanded by remember { mutableStateOf(false) }

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
                        modifier = Modifier.padding(start = 15.dp, top = 15.dp, bottom = 15.dp)
                    )

                    if (expanded) {
                        Column {
                            textField(textFieldLabel, type)
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
fun textField(label : String, type : String) {
    var text by remember { mutableStateOf("") }
    val toastText = "URL added succesfully to blocklist!"
    val toast = Toast.makeText(LocalContext.current, toastText, Toast.LENGTH_LONG) // in Activity
    Column(
        modifier = Modifier
            .padding(bottom = 16.dp, start = 10.dp)
    ) {
        val keyboardController = LocalSoftwareKeyboardController.current
        TextField(
            value = text,
            onValueChange = { newText -> text = newText },
            label = { Text(label) },
            trailingIcon = { Icon(Icons.Default.Warning, contentDescription = null)},
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
                    else RestrictedAppsManager.addRestrictedKeyord(text.toLowerCase())
                    text =""
                    keyboardController?.hide()
                    toast.show()
                }

            )
        )
    }
}

