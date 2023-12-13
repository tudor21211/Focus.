package com.example.focus.Presentation.Screens.Quiz

import android.app.usage.UsageStatsManager
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.focus.Model.Permissions.GetAppsFunctions
import com.example.focus.Presentation.Screens.Landing.Screen
import com.example.focus.R
import com.google.accompanist.drawablepainter.rememberDrawablePainter
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlin.math.floor

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun quizResponse(navController: NavController, hours: Float) {
    val systemUiController = rememberSystemUiController()
    val context = LocalContext.current


    val functions = GetAppsFunctions(
        context.packageManager,
        context.getSystemService(Context.USAGE_STATS_SERVICE) as UsageStatsManager,
        context
    )
    SideEffect {
        systemUiController.setSystemBarsColor(Color(0xFF6353F3))
        systemUiController.setNavigationBarColor(Color.Black)
    }
    val colorStops = arrayOf(
        0.2f to Color(0xFF6353F3),
        0.5f to Color(0xFF3C2EBD),
        1f to Color(0xFF190F6F)
    )
    val time = functions.getTimeSpent(7)

    var totalTimeInMillis = functions.getTotalTimeSpentAverage(time)
    var totalTimeString = functions.formatMillisecondsWithoutSeconds(totalTimeInMillis)
    var max = 0




    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.linearGradient(colorStops = colorStops)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            " $totalTimeString ",
            color = Color.Red,
            fontSize = 30.sp,
            fontFamily = FontFamily(
                Font(R.font.opensans_res)
            )
        )

        if ((hours - floor(hours)).toDouble() == 0f.toDouble()) {
            Text(
                "Your guess: ${hours.toInt()} h",
                color = Color.White,
                fontSize = 30.sp,
                fontFamily = FontFamily(
                    Font(R.font.opensans_res)
                )
            )
        } else {
            Text(
                "Your guess ${hours.toInt()} h : ${((hours - floor(hours)) * 60).toInt()} m",
                color = Color.White,
                fontSize = 30.sp,
                fontFamily = FontFamily(
                    Font(R.font.opensans_res)
                )
            )
        }

        Column(
            modifier = Modifier
                .fillMaxHeight(.8f)
                .fillMaxWidth(.9f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "What apps have you used the most?",
                color = Color.White,
                fontFamily = FontFamily(
                    Font(R.font.opensans_res)
                ),
                fontSize = 20.sp,
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth()
            )
            functions.getTopFiveMostUsedApps().forEach {
                val currentAppTimeSpent = it.timeSpentLong
                if (currentAppTimeSpent > max) {
                    max = currentAppTimeSpent.toInt()
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(5.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Image(
                        painter = rememberDrawablePainter(it.icon),
                        contentDescription = null,
                        modifier = Modifier.size(55.dp)
                    )
                    Text(
                        it.appName.toString(),
                        color = Color.White,
                        fontFamily = FontFamily(
                            Font(R.font.opensans_res)
                        ),
                        modifier = Modifier.padding(10.dp),
                        fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = it.timeSpent,
                        color = Color.White,
                        fontFamily = FontFamily(
                            Font(R.font.opensans_res)
                        ),
                    )

                }
                LinearProgressIndicator(
                    progress = it.timeSpentLong.toFloat() / max.toFloat(),
                    modifier = Modifier
                        .fillMaxWidth(.95f)
                        .padding(vertical = 4.dp),
                    trackColor = Color(0xFF2E3036),
                )
                
            }


        }
        Spacer(modifier = Modifier.fillMaxHeight(.05f))
        Button(
            onClick = {

            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0A0A05)),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 20.dp)
                .fillMaxWidth(.9f)
                .fillMaxHeight(.4f)
                .clip(shape = RoundedCornerShape(10.dp))
        ) {
            Text(
                text = "Continue",
                color = Color.White,
                fontSize = 20.sp,
                fontFamily = FontFamily(
                    Font(R.font.opensans_res)
                )
            )
        }
    }
}