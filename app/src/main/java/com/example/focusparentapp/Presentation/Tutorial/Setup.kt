package com.example.focusparentapp.Presentation.Tutorial

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.focusparentapp.QRscan.QrScanner
import com.example.focusparentapp.R
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@Composable
fun Setup(navController : NavController,context : Context) {
    val systemUiController = rememberSystemUiController()

    SideEffect{
        systemUiController.setSystemBarsColor(Color(0xFFE2E1EB))
    }

    val colorStops = arrayOf(
        0.2f to Color(0xFFE2E1EB),
        0.5f to Color(0xFFCFCDE4),
        1f to Color(0xFFC5C2DD)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = Brush.linearGradient(colorStops = colorStops))
            .padding(top = 40.dp)
        ,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ElevatedCard(
            modifier = Modifier
                .fillMaxWidth(.9f)
                .fillMaxHeight(.4f),
            colors = CardDefaults.cardColors(Color.White),
            shape = CardDefaults.elevatedShape,
            content = {
                Text(
                    text = "How to connect to the child's device?",
                    fontSize = 20.sp,
                    modifier = Modifier.padding(10.dp),
                    fontFamily = FontFamily(
                        Font(R.font.opensans_res)
                    )
                )
                Text(
                    text = "1) Install Focus Child App",
                    fontSize = 14.sp,
                    modifier = Modifier.padding(10.dp),
                    fontFamily = FontFamily(
                        Font(R.font.opensans_res)
                    )

                )
                Text(
                    text = "2) Follow the instructions in the app",
                    fontSize = 14.sp,
                    modifier = Modifier.padding(10.dp),
                    fontFamily = FontFamily(
                        Font(R.font.opensans_res)
                    )
                )
                Text(
                    text = "3) Scan the QR code displayed in the child's device",
                    fontSize = 14.sp,
                    modifier = Modifier.padding(10.dp),
                    fontFamily = FontFamily(
                        Font(R.font.opensans_res)
                    )
                )

                Row {
                    Image(painter = painterResource(id = R.drawable.focus_parentapp), contentDescription = "Focus Parent App", modifier = Modifier.padding(top = 40.dp, start = 40.dp))
                    Image(painter = painterResource(id = R.drawable.combine), contentDescription = "Focus Parent App", modifier = Modifier
                        .padding(top = 40.dp, start = 30.dp)
                        .size(80.dp))
                    Image(painter = painterResource(id = R.drawable.focus_childapp), contentDescription = "Focus Parent App", modifier = Modifier
                        .padding(top = 40.dp, start = 40.dp)
                        .size(75.dp))
                }

            },
        )

        Spacer(modifier = Modifier.fillMaxHeight(.6f))
        IconButton(
            onClick = {
                val myIntent = Intent(
                    context,
                    QrScanner::class.java
                )
                (context as Activity).startActivityForResult(myIntent, 100)
            },
            modifier = Modifier.size(100.dp).testTag("ScanQR")) {
                Icon(
                    painterResource(id = R.drawable.scan),
                    modifier = Modifier.fillMaxSize(.9f),
                    contentDescription = "Scan QR code",
                    tint = Color.White,
                )
            }
        Text(text = "Scan the QR", color = Color.White, fontSize = 20.sp, fontFamily = FontFamily(
            Font(R.font.opensans_res)),
            fontWeight = FontWeight(800)
            )
    }



}