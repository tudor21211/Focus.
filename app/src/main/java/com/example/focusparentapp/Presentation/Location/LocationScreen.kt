package com.example.focusparentapp.Presentation.Location

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.NavController
import com.example.focusparentapp.Navigation.Screens
import com.example.focusparentapp.Presentation.SharedViewModel
import com.example.focusparentapp.R
import com.example.focusparentapp.RoomDB.Entities.LocationCoordinatesEntity
import com.example.focusparentapp.RoomDB.ViewModels.AppStats
import com.example.focusparentapp.RoomDB.ViewModels.LocationCoordinates
import com.example.focusparentapp.RoomDB.ViewModels.ScreenTracker
import com.example.focusparentapp.RoomDB.ViewModels.UsersViewModel
import com.example.focusparentapp.WebSockets.WebSocketConnector
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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.math.min

@Composable
fun LocationScreen(navController: NavController, context: Context, userId: String, sharedViewModel: SharedViewModel, usersViewModel: UsersViewModel) {
    val systemUiController = rememberSystemUiController()

    val view = LocalView.current
    val window = (view.context as Activity).window

    systemUiController.setStatusBarColor(Color.Black, darkIcons = false)

    val coordinates by usersViewModel.getCoordinates(userId).collectAsState(initial = null)

    val userNumber = sharedViewModel.userNumber.collectAsState().value
    val imageResId = sharedViewModel.imageResId.collectAsState().value

    if (coordinates == null) {
        LaunchedEffect(key1 = true) {
            delay(10000)
            navController.navigate("userMenu/$userId")
        }
    }

    coordinates?.let { coords ->
        val cameraPositionState = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(LatLng(coords.longitude, coords.latitude), 15f)
        }
        val markerState = remember { mutableStateOf(MarkerState(position = LatLng(coords.longitude, coords.latitude))) }
        LaunchedEffect(coords) {
            markerState.value = MarkerState(position = LatLng(coords.longitude, coords.latitude))
            val newCameraPosition = CameraPosition.fromLatLngZoom(LatLng(coords.longitude, coords.latitude), 15f)
            cameraPositionState.animate(CameraUpdateFactory.newCameraPosition(newCameraPosition), 1000)
        }

        val mapProperties = MapProperties(
            mapStyleOptions = MapStyleOptions(styleJson)
        )

        Box(modifier = Modifier.fillMaxSize()) {
            GoogleMap(
                modifier = Modifier.fillMaxSize().statusBarsPadding(),
                cameraPositionState = cameraPositionState,
                properties = mapProperties
            ) {
                val bitmapDescriptor by remember {
                    mutableStateOf(imageResId?.let { createBitmapDescriptor(context, it) })
                }
                MarkerInfoWindowContent(
                    state = markerState.value,
                    icon = bitmapDescriptor
                ) { marker ->
                    Text(
                        "Last seen here at ${coords.timestamp}",
                        color = Color.Black,
                        modifier = Modifier.padding(16.dp),
                        fontSize = 20.sp
                    )
                }
            }
            Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth().padding(2.dp)) {
                IconButton(onClick = {
                    navController.navigate("userMenu/$userId")

                }) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                }
                IconButton(onClick = {

                        WebSocketConnector.getWebSocket()
                            ?.send("${userId}_SEND_UPDATE_LOCATION_COORDINATES")

                }) {
                    Icon(Icons.Filled.Refresh, contentDescription = "Refresh")
                }
            }
        }
    } ?: run {
        Box(modifier = Modifier.fillMaxSize().background(Color.Transparent), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
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

fun createBitmapDescriptor(context: Context, imageResId : Int): BitmapDescriptor {
    return BitmapDescriptorFactory.fromBitmap(
        BitmapFactory.decodeResource(context.resources, imageResId)
            .let { bitmap ->
                Bitmap.createScaledBitmap(bitmap, 200, 200, false)
            }
            .let { scaledBitmap ->
                Bitmap.createBitmap(scaledBitmap.width, scaledBitmap.height + 50, Bitmap.Config.ARGB_8888).also { bitmap ->
                    val canvas = Canvas(bitmap)
                    val paint = Paint().apply {
                        isAntiAlias = true
                        color = Color.Cyan.toArgb()
                        strokeWidth = 5f
                    }
                    val radius = min(scaledBitmap.width, scaledBitmap.height) / 2f
                    canvas.drawCircle(scaledBitmap.width / 2f, scaledBitmap.height / 2f, radius, paint)
                    val bitmapPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
                        xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
                    }
                    canvas.drawBitmap(scaledBitmap, 0f, 0f, bitmapPaint)
                    paint.color = Color.Red.toArgb()
                    val path = Path().apply {
                        moveTo(scaledBitmap.width / 2f, scaledBitmap.height.toFloat() + 1000)
                        lineTo(scaledBitmap.width / 2f, scaledBitmap.height.toFloat() + 1500)
                    }
                    canvas.drawPath(path, paint)
                    paint.color = Color.Red.toArgb()
                    canvas.drawCircle(scaledBitmap.width / 2f, scaledBitmap.height.toFloat() + 30, 15f, paint)
                }
            }
    )
}