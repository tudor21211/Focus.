package com.example.focusparentapp.Presentation

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
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.focusparentapp.Navigation.Screens
import com.example.focusparentapp.R
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun LandingScreen(navController: NavController) {

    val motivation by AnimatedLandingPage.motivation.collectAsState(initial = "Setting limits helps children learn self-discipline.")

    val systemUiController = rememberSystemUiController()

    SideEffect {
        systemUiController.setSystemBarsColor(Color(0xFF314670))
    }
    val colorStops = arrayOf(
        0.2f to Color(0xFF314670),
        0.5f to Color(0xFF2E477A),
        1f to Color(0xFF203868)
    )


    val openSans = FontFamily(
        Font(R.font.opensans_res),
    )

    Box(
        modifier = Modifier
            .fillMaxSize(1f)
            .background(Brush.linearGradient(colorStops = colorStops))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(1f)
                .padding(top = 50.dp)
        ) {

            AnimatedContent(
                targetState = motivation,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxHeight(.2f),
                transitionSpec = {
                    addAnimation().using(
                        SizeTransform(clip = false)
                    )
                }, label = ""
            ) { targetCount ->
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


            Spacer(modifier = Modifier.weight(.1f))

            Button(
                onClick = {
                    navController.navigate(Screens.TutorialPager.route)
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0A0A05)),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 20.dp)
                    .fillMaxWidth(.9f)
                    .fillMaxHeight(.1f)
                    .clip(shape = RoundedCornerShape(10.dp))
                    .testTag("Get Started")
            ) {
                Text(
                    text = "Get Started",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.opensans_res))
                )
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
