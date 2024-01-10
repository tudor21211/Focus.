package com.example.focus.Presentation.Screens.MainPage

import android.app.usage.UsageStatsManager
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.focus.Model.Permissions.GetAppsFunctions
import com.example.focus.Presentation.Screens.Landing.Screen
import com.example.focus.R
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun mainPageScreen(navController: NavController) {

    val systemUiController = rememberSystemUiController()
    val context = LocalContext.current

    val colorStops = arrayOf(
        0.2f to Color(0xFF0E0653),
        0.5f to Color(0xFF090341),
        1f to Color(0xFF070231)
    )

    SideEffect {
        systemUiController.setSystemBarsColor(Color(0xFF0E0653))
    }

    val stroke = Stroke(
        width = 2f,
        pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
    )

    val functions = GetAppsFunctions(
        context.packageManager,
        context.getSystemService(Context.USAGE_STATS_SERVICE) as UsageStatsManager,
        context
    )
    val time = functions.getTimeSpent(0)
    var totalTimeInMillis = functions.getTotalTimeSpent(time)

    functions.getInstalledApps()

    val launchCount = functions.allAppsLaunchTracker(functions.getNonSystemApps())

    Column(
        modifier = Modifier
            .background(Brush.linearGradient(colorStops = colorStops))
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Spacer(modifier = Modifier.fillMaxHeight(.15f))

        Box(
            modifier = Modifier
                .fillMaxWidth(.95f)
                .fillMaxHeight(.3f)
        ) {
            Row(

            ) {
                Card(
                    modifier = Modifier
                        .padding(end = 4.dp)
                        .border(
                            border = BorderStroke(1.dp, Color.White),
                            shape = RoundedCornerShape(15.dp)
                        )
                        .fillMaxWidth(.5f)
                        .fillMaxHeight(.6f),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFF16288A)
                    )
                ) {
                    Text(
                        text = "Apps Launch Tracker ",
                        modifier = Modifier.padding(10.dp),
                        fontFamily = FontFamily(Font(R.font.opensans_res))
                    )

                    Text(
                        text = "$launchCount ",
                        modifier = Modifier.padding(10.dp),
                        fontFamily = FontFamily(Font(R.font.opensans_res)),
                        fontSize = 30.sp
                    )


                }

                Card(
                    modifier = Modifier
                        .padding(start = 4.dp)
                        .border(
                            border = BorderStroke(1.dp, Color.White),
                            shape = RoundedCornerShape(15.dp)
                        )
                        .fillMaxWidth(1f)
                        .fillMaxHeight(.6f),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFF0E1B5F)
                    )
                ) {
                    Text(
                        text = "Screen Time ",
                        modifier = Modifier.padding(10.dp),
                        fontFamily = FontFamily(Font(R.font.opensans_res))
                    )

                    Text(
                        text = totalTimeInMillis,
                        modifier = Modifier.padding(10.dp),
                        fontFamily = FontFamily(Font(R.font.opensans_res)),
                        fontSize = 30.sp
                    )
                }
            }
        }

        Box(
            modifier = Modifier
                .drawBehind {
                    drawRoundRect(
                        color = Color.White,
                        style = stroke,
                        cornerRadius = CornerRadius(15.dp.toPx())
                    )
                }

                .fillMaxWidth(.95f)
                .fillMaxHeight(.25f)
        ) {
            Column(
                Modifier.align(Alignment.TopCenter),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Block blacklisted apps", modifier = Modifier
                        .padding(10.dp),
                    fontSize = 20.sp, fontFamily = FontFamily(Font(R.font.opensans_res))
                )
                Row(modifier = Modifier.padding(top = 5.dp)) {
                    Button(
                        onClick = { /*TODO*/ },
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(Color(0xFF0649AD)),
                        modifier = Modifier.padding(end = 25.dp)
                    ) {
                        Text(text = "Start", color = Color.White)
                    }
                    Button(
                        onClick = { try {
                            navController.navigate(Screen.AllAppsScreen.route)
                        }catch (e : Exception) {
                            println("Cant go to because : " + e)
                        }
                                  },
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(Color(0xFF0649AD))
                    ) {
                        Text(text = "Manage blocklist", color = Color.White)
                    }
                }
            }

        }

        Spacer(modifier = Modifier.fillMaxHeight(.07f))

        Box(
            modifier = Modifier
                .drawBehind {
                    drawRoundRect(
                        color = Color.White,
                        style = stroke,
                        cornerRadius = CornerRadius(15.dp.toPx())
                    )
                }

                .fillMaxWidth(.95f)
                .fillMaxHeight(.30f)
        ) {
            Column(
                Modifier.align(Alignment.TopCenter),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Restrict time consuming apps", modifier = Modifier
                        .padding(5.dp),
                    fontSize = 20.sp, fontFamily = FontFamily(Font(R.font.opensans_res))
                )
                Row(modifier = Modifier.padding(top = 5.dp)) {
                    Button(
                        onClick = { /*TODO*/ },
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(Color(0xFF0649AD))
                    ) {
                        Text(text = "Manage", color = Color.White)
                    }
                }
            }
        }

    }


}