package com.example.focus.Presentation.Screens.Quiz

import android.content.SharedPreferences
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.focus.Presentation.Screens.Landing.Screen
import com.example.focus.R
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import java.util.concurrent.TimeUnit

@Composable
fun quizAdvice(
    navController: NavController, inheritedAverageTime: Float, sharedPreferences: SharedPreferences
) {

    val timeSpentInTenYears =
        TimeUnit.MILLISECONDS.toHours(inheritedAverageTime.toLong() * 365 * 10)
    val daysSpent = TimeUnit.MILLISECONDS.toDays(inheritedAverageTime.toLong() * 365 * 10)

    val systemUiController = rememberSystemUiController()
    val colorStops = arrayOf(
        0.2f to Color(0xFF6353F3), 0.5f to Color(0xFF3C2EBD), 1f to Color(0xFF190F6F)
    )

    SideEffect {
        systemUiController.setSystemBarsColor(Color(0xFF6353F3))
        systemUiController.setNavigationBarColor(Color.Black)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.linearGradient(colorStops = colorStops)),
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {

        Text(
            text = "Based on this calculations, in 10 years you lose $timeSpentInTenYears hours of your life.",
            color = Color.White,
            fontSize = 25.sp,
            modifier = Modifier.padding(20.dp),
            textAlign = androidx.compose.ui.text.style.TextAlign.Center,
        )
        Spacer(modifier = Modifier.fillMaxSize(.3f))
        Text(
            text = " %.1f".format(daysSpent / 365.0),
            color = Color.Red,
            fontWeight = FontWeight(900),
            fontSize = 60.sp
        )
        Text(
            text = "*years\n spent on phone ",
            fontSize = 24.sp,
            color = Color.White,
            fontWeight = FontWeight(600),
            textAlign = androidx.compose.ui.text.style.TextAlign.Center,
        )
        Spacer(modifier = Modifier.fillMaxSize(.6f))
        Button(
            onClick = {
                sharedPreferences.edit().putBoolean("QuizFinished", true).apply()
                println("Quiz finished: ${sharedPreferences.getBoolean("QuizFinished", false)}")
                navController.navigate(Screen.AllAppsScreen.route)
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0A0A05)),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 60.dp)
                .fillMaxWidth(.9f)
                .fillMaxHeight(.5f)
                .clip(shape = RoundedCornerShape(10.dp))
        ) {
            Text(
                text = "Continue", color = Color.White, fontSize = 20.sp, fontFamily = FontFamily(
                    Font(R.font.opensans_res)
                )
            )
        }

    }


}