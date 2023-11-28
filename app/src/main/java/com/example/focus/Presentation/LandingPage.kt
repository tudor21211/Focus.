package com.example.focus.Presentation

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.with
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.focus.R
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun landingPage (navController: NavController) {

    val motivation by AnimatedLandingPage.motivation.collectAsState(initial = "Enjoy free time without distractions")

    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setStatusBarColor(Color(0xFF6353F3))
        systemUiController.setNavigationBarColor(Color.Black)
    }


    val openSans = FontFamily(
        Font(R.font.opensans_res),
    )
    val colorStops = arrayOf(
        0.2f to Color(0xFF6353F3),
        0.5f to Color(0xFF3C2EBD),
        1f to Color(0xFF190F6F)
    )
    Box(modifier = Modifier
        .fillMaxSize(1f)
        .background(Brush.linearGradient(colorStops = colorStops))) {
        Column(
            modifier = Modifier
                .fillMaxSize(1f)
                .padding(top = 50.dp)
        ) {

            AnimatedContent(
                targetState = motivation,
                modifier = Modifier.align(Alignment.CenterHorizontally).fillMaxHeight(.2f),
                transitionSpec = {
                    addAnimation().using(
                        SizeTransform(clip = false)
                    )
                }, label = ""
            ){ targetCount ->
                Text(
                    text = "$targetCount",
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    fontFamily = openSans,
                    fontSize = 20.sp,
                    modifier = Modifier
                        .padding(horizontal = 12.dp)
                        .align(Alignment.CenterHorizontally),
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = "Focus.",
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .align(Alignment.CenterHorizontally)
                    .weight(1f),
                color = Color.White,
                fontFamily = openSans,
                fontSize = 100.sp
            )


            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = { navController.navigate(Screen.PermissionsScreen.route) },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0A0A05)),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 20.dp)
                    .size(width = 400.dp, height = 50.dp)
                    .clip(shape = RoundedCornerShape(10.dp))
                    ) {
                Text(text = "Get Started", color = Color.White, fontSize = 20.sp, fontFamily = openSans)
            }
            Spacer(modifier = Modifier.weight(.1f))
        }




    }


}


@ExperimentalAnimationApi
fun addAnimation(duration: Int = 150): ContentTransform {
    return slideInVertically(animationSpec = tween(durationMillis = duration)) { height -> height } + fadeIn(
        animationSpec = tween(durationMillis = duration)
    ) with slideOutVertically(animationSpec = tween(durationMillis = duration)) { height -> -height } + fadeOut(
        animationSpec = tween(durationMillis = duration)
    )
}



