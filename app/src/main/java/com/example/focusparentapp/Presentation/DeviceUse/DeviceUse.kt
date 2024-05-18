package com.example.focusparentapp.Presentation.DeviceUse

import android.graphics.drawable.shapes.Shape
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
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
import androidx.navigation.NavController
import com.example.focusparentapp.R
import com.example.focusparentapp.RoomDB.ViewModels.UsersViewModel
import com.google.accompanist.drawablepainter.rememberDrawablePainter
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun DeviceUse(navController: NavController){

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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = Brush.linearGradient(colorStops = colorStops))
    ) {

        hardcodedTimeArraySorted.forEach {
            if(it > maxTimeSpent)
                maxTimeSpent = it

            var progress = (it.toFloat())/(maxTimeSpent.toFloat())

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(5.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.focus_childapp),
                    contentDescription = null,
                    modifier = Modifier.size(47.dp)
                )
                Text(
                    "Hardcoded Name",
                    color = Color.White,
                    fontFamily = FontFamily(
                        Font(R.font.opensans_res)
                    ),
                    modifier = Modifier.padding(10.dp),
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = it.toString(),
                    color = Color.White,
                    fontFamily = FontFamily(
                        Font(R.font.opensans_res)
                    ),
                )

            }
//            LinearProgressIndicator(
//                progress = progress,
//                modifier = Modifier
//                    .fillMaxWidth(.95f)
//                    .padding(vertical = 4.dp)
//                    .background(Color(0xFFE41010)),
//                trackColor = Color(0xFF2E3038),
//                color = Color.Red
//            )
            CustomLinearProgressIndicator(progress = progress,modifier = Modifier.fillMaxWidth(.95f))
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