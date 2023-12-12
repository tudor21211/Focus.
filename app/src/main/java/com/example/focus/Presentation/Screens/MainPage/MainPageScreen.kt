package com.example.focus.Presentation.Screens.MainPage

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun mainPageScreen() {
    Box(Modifier.fillMaxSize()){
        Text(text = "Main Page", color = Color.White)
    }
}