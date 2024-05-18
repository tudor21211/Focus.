package com.example.focusparentapp.Presentation.DeviceUse

import android.graphics.drawable.shapes.Shape
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.focusparentapp.Navigation.Screens
import com.example.focusparentapp.Presentation.MainPage.TopBar
import com.example.focusparentapp.R
import com.example.focusparentapp.RoomDB.ViewModels.AppInfo
import com.example.focusparentapp.RoomDB.ViewModels.AppStats
import com.example.focusparentapp.RoomDB.ViewModels.ScreenTracker
import com.example.focusparentapp.RoomDB.ViewModels.UsersViewModel
import com.example.focusparentapp.Utils.Utils
import com.google.accompanist.drawablepainter.rememberDrawablePainter
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun DeviceUse(navController: NavController, userId: String, usersViewModel: UsersViewModel){

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

    //TODO remove hardcoded time array
    val hardcodedTimeArray = listOf(150, 1800, 300, 3000, 4000)
    val hardcodedTimeArraySorted = hardcodedTimeArray.sortedDescending()
    var maxTimeSpent = 0
    var screenTracker by remember {
        mutableStateOf<ScreenTracker>(ScreenTracker(0,""))
    }
    var appsStats by remember {
        mutableStateOf<List<AppStats>>(emptyList())
    }
    LaunchedEffect(Unit) {
        withContext(Dispatchers.IO) {
            screenTracker = usersViewModel.getScreenTimeTracker(userId)
            appsStats = usersViewModel.getStatsFromUser(userId)
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = Brush.linearGradient(colorStops = colorStops))
    ) {
        com.example.focusparentapp.Presentation.Apps.TopBar(
            navController = navController,
            route = "userMenu/${userId}",
            lastUpdateDate = ""
        )

        Spacer(modifier = Modifier.fillMaxHeight(.08f))
        Column (
            modifier = Modifier.padding(
                start = screenWidth * 0.05f,
                end = screenWidth * 0.05f,
                top = screenWidth * 0.05f,
                bottom = screenWidth * 0.05f
            )
        ){

            Row() {
                Card(
                    modifier = Modifier
                        .padding(end = 4.dp)
                        .border(
                            border = BorderStroke(1.dp, Color.White),
                            shape = RoundedCornerShape(15.dp)
                        )
                        .fillMaxWidth(.5f)
                        .fillMaxHeight(.2f),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFF22355C)
                    )
                ) {

                    Text(
                        text = "Apps Launch Tracker ",
                        modifier = Modifier.padding(10.dp),
                        fontFamily = FontFamily(Font(R.font.opensans_res)),
                        color = Color.White
                    )
                    Row() {
                        Text(
                            text = screenTracker.launchTracker.toString(),
                            modifier = Modifier.padding(10.dp),
                            fontFamily = FontFamily(Font(R.font.opensans_res)),
                            fontSize = 30.sp,
                            color = Color.White
                        )
                        Text(
                            text = "launches /24h",
                            fontFamily = FontFamily(Font(R.font.opensans_res)),
                            modifier = Modifier.padding(start = 15.dp, top = 40.dp),
                            fontSize = 12.sp,
                            color = Color.White
                        )
                    }


                }
            println("SCREEN TRACKER , ${screenTracker.screenTime}")
                Card(
                    modifier = Modifier
                        .padding(start = 4.dp)
                        .border(
                            border = BorderStroke(1.dp, Color.White),
                            shape = RoundedCornerShape(15.dp)
                        )
                        .fillMaxWidth(1f)
                        .fillMaxHeight(.2f),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFF121B2E)
                    )
                ) {
                    Text(
                        text = "Screen Time",
                        modifier = Modifier.padding(10.dp),
                        fontFamily = FontFamily(Font(R.font.opensans_res)),
                        color = Color.White
                    )

                    Text(
                        text = screenTracker.screenTime,
                        modifier = Modifier.padding(10.dp),
                        fontFamily = FontFamily(Font(R.font.opensans_res)),
                        fontSize = 30.sp,
                        color = Color.White
                    )
                }
            }

            Spacer(modifier = Modifier.fillMaxHeight(.08f))

            appsStats.forEach {
                if (it.oneDayStats > maxTimeSpent)
                    maxTimeSpent = it.oneDayStats.toInt()

                var progress = (it.oneDayStats.toFloat()) / (maxTimeSpent.toFloat())

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(5.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Image(
                        painter = rememberImagePainter(data = Utils().byteStringToDrawable(it.icon)),
                        contentDescription = null,
                        modifier = Modifier.size(47.dp)
                    )
                    Text(
                        it.appName,
                        color = Color.White,
                        fontFamily = FontFamily(
                            Font(R.font.opensans_res)
                        ),
                        modifier = Modifier.padding(10.dp),
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = it.oneDayStats.toString(),
                        color = Color.White,
                        fontFamily = FontFamily(
                            Font(R.font.opensans_res)
                        ),
                    )

                }

                CustomLinearProgressIndicator(
                    progress = progress,
                    modifier = Modifier.fillMaxWidth(.95f)
                )
                }
            }
        }
    }


@Composable
fun CustomLinearProgressIndicator(
    modifier: Modifier = Modifier,
    progress: Float,
    progressColor: Color = Color(0xFFF00D0D),
    backgroundColor: Color = Color(0xFF680000),
    clipShape: RoundedCornerShape = RoundedCornerShape(16.dp)
) {
    Box(
        modifier = modifier
            .clip(clipShape)
            .background(backgroundColor)
            .height(8.dp)
    ) {
        Box(
            modifier = Modifier
                .background(progressColor)
                .fillMaxHeight()
                .fillMaxWidth(progress)
        )
    }
}


@Composable
fun statsWidget(){
    Card(
        modifier = Modifier
            .padding(start = 4.dp)
            .border(
                border = BorderStroke(1.dp, Color.White),
                shape = RoundedCornerShape(15.dp)
            )
            .fillMaxWidth(.5f)
            .fillMaxHeight(.5f),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF0E1B5F)
        )
    ) {
        Text(
            text = "Screen Time ",
            modifier = Modifier.padding(10.dp),
            fontFamily = FontFamily(Font(R.font.opensans_res)),
            color = Color.White
        )

        Text(
            text = "totalTimeInMillis",
            modifier = Modifier.padding(10.dp),
            fontFamily = FontFamily(Font(R.font.opensans_res)),
            fontSize = 30.sp,
            color = Color.White
        )
    }
}