package com.example.focusparentapp.Presentation.Tutorial

import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.absoluteValue
import androidx.compose.ui.util.lerp
import androidx.navigation.NavController
import com.example.focusparentapp.Navigation.Screens
import com.example.focusparentapp.R
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TutorialPager(navController: NavController) {

    val systemUiController = rememberSystemUiController()


    SideEffect {
        systemUiController.setSystemBarsColor(Color(0xFF0E0653))
        systemUiController.setNavigationBarColor(Color.Black)
    }

    val colorStops1 = arrayOf(
        0.2f to Color(0xFF0E0653),
        0.5f to Color(0xFF090341),
        1f to Color(0xFF070231)
    )

    val colorStops2 = arrayOf(
        0.2f to Color(0xFF37363F),
        0.5f to Color(0xFF413F4E),
        1f to Color(0xFF45435C)
    )

    val pageTexts = listOf(
        "Block access to apps with one tap",
        "Control websites and set specific blacklisted keywords",
        "Check your child's activity and set time limits",
        "Protect your child from harmful content and set up a safe environment"
    )

    val images = listOf(
        R.drawable.block,
        R.drawable.websites,
        R.drawable.stats,
        R.drawable.safe
    )

    val pagerState = rememberPagerState(initialPage =
        0
    )
    Row(
        Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(4) { // Change 4 to your actual number of pages
            val isCurrentPage = pagerState.currentPage == it
            val color = if (pagerState.currentPage == it) {
                Color.White // Current page color
            } else {
                Color.Gray // Other page color
            }
            Spacer(modifier = Modifier.size(8.dp))
            Circle(
                //modifier = Modifier.size(12.dp),
                color = color,
                isCurrentPage = isCurrentPage
            )
        }
    }
    HorizontalPager(state = pagerState, pageCount = 4) { page ->
        Card(
            Modifier
                .fillMaxSize(1f)
                .background(Brush.linearGradient(colorStops = colorStops1))
                .graphicsLayer {

                    val pageOffset = (
                            (pagerState.currentPage - page) + pagerState
                                .currentPageOffsetFraction
                            ).absoluteValue

                    alpha = lerp(
                        start = 0.5f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    )
                },
            colors = CardDefaults.cardColors(
                containerColor = Color.Transparent
            )
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.padding(start = 5.dp, end = 5.dp)
                ) {
                Spacer(modifier = Modifier.fillMaxHeight(.3f))
                Image(painter = painterResource(id = images[page]), contentDescription = "" )
                Spacer(modifier = Modifier.fillMaxHeight(.2f))
                Text(
                    text = pageTexts[page],
                    color = Color.White,
                    fontFamily = FontFamily(Font(R.font.opensans_res)),
                    fontSize = 30.sp, fontWeight = FontWeight(700),
                    textAlign = TextAlign.Center,
                    lineHeight = 32.sp
                    )
            }

        }

    }
    Column {
        Spacer(modifier = Modifier.fillMaxHeight(.8f))
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            if (pagerState.currentPage < 3) {
                repeat(4) {
                    val isCurrentPage = pagerState.currentPage == it
                    val color = if (pagerState.currentPage == it) {
                        Color.White
                    } else {
                        Color.Gray
                    }
                    Spacer(modifier = Modifier.size(8.dp))
                    Box(Modifier.padding(top = if (isCurrentPage) 0.dp else 2.dp)) {
                        Circle(
                            color = color,
                            isCurrentPage = isCurrentPage
                        )
                    }

                }
            }
            else
            {
                Button(
                    onClick = { navController.navigate(Screens.Setup.route) },
                    colors = ButtonDefaults.buttonColors(Color.White),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.fillMaxWidth(.85f).padding(top=80.dp).height(50.dp)
                ) {
                    Text(text = "Start", color = Color.Black, fontSize = 18.sp, fontFamily = FontFamily(Font(R.font.opensans_res)))

                }
            }
        }
    
    }
   
}

@Composable
fun Circle(
    modifier: Modifier = Modifier,
    color: Color = Color.Gray,
    isCurrentPage: Boolean = true
) {
    val size = if (isCurrentPage) 18.dp else 12.dp
    Box(
        modifier = modifier
            .size(size)
            .clip(CircleShape)
            .background(color)
    )
}