package com.example.focusparentapp.Presentation

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun DeviceBound(navController: NavController) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                start = screenWidth * 0.05f,
                end = screenWidth * 0.05f,
                top = screenWidth * 0.05f,
                bottom = screenWidth * 0.05f
            )
            .border(1.dp, Color.Black)
    ) {
        Text(text = "Device {} bound", modifier = Modifier.padding(16.dp), fontSize = 36.sp)
    }
}
