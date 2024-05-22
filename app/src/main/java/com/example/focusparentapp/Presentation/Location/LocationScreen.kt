package com.example.focusparentapp.Presentation.Location

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.NavController
import com.example.focusparentapp.R
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.PinConfig
import com.google.maps.android.compose.AdvancedMarker
import com.google.maps.android.compose.Circle
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerInfoWindowContent
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import kotlinx.coroutines.launch
import kotlin.math.min

@Composable
fun LocationScreen(navController: NavController,context: Context) {
    val systemUiController = rememberSystemUiController()
    val coroutineScope = rememberCoroutineScope()
    // Set the status bar to transparent
    systemUiController.setSystemBarsColor(
        color = Color.White,
        darkIcons = true
    )


    systemUiController.isStatusBarVisible = false // Status bar
    systemUiController.isNavigationBarVisible = false // Navigation bar
    systemUiController.isSystemBarsVisible = false // Status & Navigation bars
    systemUiController.navigationBarDarkContentEnabled = false

    val cameraPosition = LatLng(45.777795871949714, 21.228655029975442)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(cameraPosition, 15f)
    }
    LaunchedEffect(cameraPositionState) {
        cameraPositionState.animate(
            update = CameraUpdateFactory.newCameraPosition(
                CameraPosition(cameraPosition, 20f, 0f, 0f)
            ),
            durationMs = 1000
        )
    }
    val mapProperties = MapProperties(
        mapStyleOptions = MapStyleOptions(styleJson)
    )
    Box(modifier = Modifier.fillMaxSize()) {
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            properties = mapProperties,

            ) {
            val bitmapDescriptor by remember {
                mutableStateOf(createBitmapDescriptor(context))
            }
            println("BITMAP DESCRIPTOR $bitmapDescriptor")
            MarkerInfoWindowContent(
                state = MarkerState(position = cameraPosition),
                icon = bitmapDescriptor
            ){
                marker ->
                Text("Last seen here at 5/22/2024 13:22", color = Color.Black, modifier = Modifier.padding(16.dp), fontSize = 20.sp)
            }
        }
        IconButton(onClick = { /* Handle back button click here */ }) {
            Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
        }
    }




}

val styleJson = """
    [
        {
            "featureType": "poi",
            "elementType": "labels",
            "stylers": [
                {
                    "visibility": "on"
                }
            ]
        },
        {
            "featureType": "water",
            "elementType": "geometry",
            "stylers": [
                {
                    "color": "#CDE2F0"
                }
            ]
        },
        {
            "featureType": "poi",
            "elementType": "labels.icon",
            "stylers": [
                {
                    "visibility": "on"
                }
            ]
        }
    ]
""".trimIndent()



fun createBitmapDescriptor(context: Context): BitmapDescriptor {
    return BitmapDescriptorFactory.fromBitmap(
        BitmapFactory.decodeResource(context.resources, R.drawable.girl)
            .let { bitmap ->
                Bitmap.createScaledBitmap(bitmap, 200, 200, false) // Adjust the size as needed
            }
            .let { scaledBitmap ->
                Bitmap.createBitmap(scaledBitmap.width, scaledBitmap.height + 50, Bitmap.Config.ARGB_8888)
                    .also { bitmap ->
                        val canvas = Canvas(bitmap)
                        val paint = Paint().apply {
                            isAntiAlias = true
                            color = Color.Cyan.toArgb() // Marker background color
                            strokeWidth = 5f
                        }
                        val radius = min(scaledBitmap.width, scaledBitmap.height) / 2f
                        canvas.drawCircle(
                            scaledBitmap.width / 2f,
                            scaledBitmap.height / 2f,
                            radius,
                            paint
                        )
                        val bitmapPaint = Paint(Paint.ANTI_ALIAS_FLAG)
                        bitmapPaint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
                        canvas.drawBitmap(scaledBitmap, 0f, 0f, bitmapPaint)

                        // Draw the pinpoint
                        paint.color = Color.Red.toArgb() // Pinpoint color
                        val path = Path().apply {
                            moveTo(scaledBitmap.width / 2f, scaledBitmap.height.toFloat()+1000)
                            lineTo(scaledBitmap.width / 2f, scaledBitmap.height.toFloat() + 1500)
                        }
                        canvas.drawPath(path, paint)
                        paint.color = Color.Red.toArgb() // Circle color
                        canvas.drawCircle(scaledBitmap.width / 2f, scaledBitmap.height.toFloat() + 30, 15f, paint)
                    }
            })
}