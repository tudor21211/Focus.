package com.example.focusparentapp.Presentation.Location

import android.view.Window
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.PinConfig
import com.google.maps.android.compose.AdvancedMarker
import com.google.maps.android.compose.Circle
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

//import com.google.android.gms.maps.model.CameraPosition
//import com.google.android.gms.maps.model.LatLng
//import com.google.maps.android.compose.GoogleMap
//import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun LocationScreen(window : Window) {
    val systemUiController = rememberSystemUiController()

    // Set the status bar to transparent
    systemUiController.setSystemBarsColor(
        color = Color.White,
        darkIcons = true
    )
    val windowInsetsController = remember { WindowInsetsControllerCompat(window, window.decorView) }

    // Hide the navigation bar
    windowInsetsController.hide(WindowInsetsCompat.Type.navigationBars())

    systemUiController.isStatusBarVisible = false // Status bar
    systemUiController.isNavigationBarVisible = false // Navigation bar
    systemUiController.isSystemBarsVisible = false // Status & Navigation bars
    systemUiController.navigationBarDarkContentEnabled = false

    val cameraPosition = LatLng(45.777795871949714, 21.228655029975442)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(cameraPosition, 15f)
    }
    Box(modifier = Modifier.fillMaxSize()) {
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState
        ) {
            var circleCenter by remember { mutableStateOf(cameraPosition) }

            Circle(
                center = circleCenter,
                fillColor = Color.Transparent,
                strokeColor = Color.Gray,
                radius = 100.0,
            )
            AdvancedMarker(
                state = MarkerState(position = cameraPosition),
                title = "Was here 5/22/2024 at 13:20",
            )
        }

    }

}