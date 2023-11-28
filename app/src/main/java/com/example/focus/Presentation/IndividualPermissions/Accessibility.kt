package com.example.focus.Presentation.IndividualPermissions

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.provider.Settings
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.focus.Presentation.GifImage
import com.example.focus.R
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun accessibilityPermission () {
    val context = LocalContext.current
    val systemUiController = rememberSystemUiController()
    val colorStops = arrayOf(
        0.2f to Color(0xFF0E0653),
        0.5f to Color(0xFF090341),
        1f to Color(0xFF070231)
    )
    val colorStopsButton = arrayOf(
        0.2f to Color(0xFFB3A3EB),
        0.5f to Color(0xFF423E69),
        1f to Color(0xFF50458A)
    )
    val requestCode = 200
    SideEffect {
        systemUiController.setSystemBarsColor(Color(0xFF0E0653))
    }
    val listOfInstructions = listOf(
        "Click the button below to open the Settings app.",
        "Find Focus. in the list of apps and click on it.",
        "Activate the permission in the new tab.",
        "The accesibility setting should be set to \"On\""
    )

    Column(modifier = Modifier
        .background(Brush.linearGradient(colorStops = colorStops))
        .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        Text(text = "To offer you a seamless and accessible experience, Focus requires 'Accessibility' permission. This allows us to detect when apps are opened, enabling precise blocking during your focused sessions. Your privacy matters, and we handle this information responsibly, solely for optimizing your productivity. Thank you for trusting us to help you stay focused and achieve your goals!",
            color = Color.White,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Justify,
            fontSize = 19.sp,
            fontFamily = FontFamily(Font(R.font.opensans_res))
        )
        Spacer (modifier = Modifier.fillMaxHeight(.10f))
        Image(painter = painterResource(id = R.drawable.info), contentDescription = "info image",Modifier.fillMaxSize(.1f))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            Text(text = "How to activate the permission?",
                color = Color.White,
                fontSize = 24.sp,
                fontFamily = FontFamily(Font(R.font.opensans_res))
            )
        }

        Column() {
            listOfInstructions.forEach {
                Row() {
                    Text(text = "\u2022",
                        textAlign = TextAlign.Center,
                        modifier = Modifier.width(20.dp),
                        color = Color.White,
                        fontSize = 19.sp
                    )
                    Text(
                        text = it,
                        textAlign = TextAlign.Justify,
                        modifier = Modifier.weight(1f, fill = true),
                        color = Color.White,
                        fontFamily = FontFamily(Font(R.font.opensans_res)),
                        fontSize = 16.sp
                    )
                }
            }
        }
        Spacer(modifier = Modifier.fillMaxHeight(.1f))
        GifImage(modifier = Modifier.fillMaxWidth(),R.drawable.accessibility)
        Spacer(modifier = Modifier.fillMaxHeight(.1f))
        Button(
            onClick = {
                val settingsIntent = Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS)
                (context as Activity).startActivityForResult(settingsIntent, requestCode)},
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0A0A05)),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 20.dp)
                .fillMaxWidth(.8f)
                .fillMaxHeight(.6f)
                .clip(shape = RoundedCornerShape(10.dp))
                .border(
                    BorderStroke(
                        1.dp,
                        brush = Brush.linearGradient(colorStops = colorStopsButton)
                    ), RoundedCornerShape(30.dp)
                )
        ) {
            Text(text = "Go to settings", color = Color.White, fontSize = 20.sp, fontFamily = FontFamily(
                Font(R.font.opensans_res)
            )
            )
        }

    }



}