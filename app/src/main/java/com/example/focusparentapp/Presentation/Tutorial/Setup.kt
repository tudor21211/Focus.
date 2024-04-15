package com.example.focusparentapp.Presentation.Tutorial

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun Setup(navController : NavController) {

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
                .fillMaxHeight(.4f)
                ,
            colors = CardDefaults.cardColors(Color.White),
            shape = CardDefaults.elevatedShape,
            content = {
                Text(
                    text = "How to connect to the child's device?",
                    fontSize = 20.sp,
                    modifier = Modifier.padding(10.dp)
                )
            },
        )
    }



}