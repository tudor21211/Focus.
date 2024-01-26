package com.example.focus.Presentation.Screens.MainPage

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.focus.R
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun timeConsumingApps() {
    var myState1 by remember { mutableStateOf(false) }
    var myState2 by remember { mutableStateOf(false) }
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
        modifier = Modifier.fillMaxSize().background(Brush.linearGradient(colorStops = colorStops))
    ) {
        Spacer(
            modifier = Modifier.fillMaxSize(.05f)
        )
        Image(
            painter = painterResource(id = R.drawable.insta),
            contentDescription = null,
            Modifier
                .fillMaxWidth(.2f)
                .padding(bottom = 30.dp, start = 10.dp)
        )

        Row() {
            Text(text = "Block Instagram Reels", fontFamily = FontFamily(Font(R.font.opensans_res)), fontSize = 30.sp, modifier = Modifier.padding(start = 10.dp))
            Checkbox(
                checked = myState1,
                onCheckedChange = { myState1 = it },
                colors = CheckboxDefaults.colors(
                    uncheckedColor = Color.Red,
                    checkedColor = Color.Green,
                    checkmarkColor = Color.Black
                ),
                modifier = Modifier.size(45.dp)

            )

        }
        Spacer(
            modifier = Modifier.fillMaxSize(.05f)
        )
        Image(
            painter = painterResource(id = R.drawable.yt),
            contentDescription = null,
            Modifier
                .fillMaxWidth(.2f)
                .padding(bottom = 30.dp, start = 10.dp)
        )
        Row() {
            Text(text = "Block YouTube Shorts", fontFamily = FontFamily(Font(R.font.opensans_res)), fontSize = 30.sp, modifier = Modifier.padding(start = 10.dp))
            Checkbox(
                checked = myState2,
                onCheckedChange = { myState2 = it },
                colors = CheckboxDefaults.colors(
                    uncheckedColor = Color.Red,
                    checkedColor = Color.Green,
                    checkmarkColor = Color.Black
                )
            )

        }

        Text(
            text = "\n\nIntroducing a new feature in your app! We understand that Instagram and YouTube have become integral parts of your digital experience, but we also recognize the growing concern of excessive time spent on certain features. With our latest functionality, we've devised a solution that addresses this issue without completely blocking these popular platforms.\n" +
                    "\n" +
                    "Unlike traditional blockers that restrict entire apps, our innovative approach specifically targets the time-consuming aspects of Instagram and YouTube—Reels and Shorts. These engaging features, while entertaining, can often lead to extended usage and potential productivity challenges.",
        modifier = Modifier.padding(top = 50.dp, start = 10.dp, end = 10.dp),
            fontFamily = FontFamily(Font(R.font.opensans_res)),
            textAlign = TextAlign.Center,
            fontSize = 13.sp)

    }
}

