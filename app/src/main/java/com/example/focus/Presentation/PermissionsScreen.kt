package com.example.focus.Presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.focus.Model.Permissions.PermissionFunctions
import com.example.focus.R
import com.example.focus.Services.MyAccessibilityService
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import java.security.Permissions

@Composable
fun permissionScreen(navController: NavController) {
    val systemUiController = rememberSystemUiController()
    val context = LocalContext.current
    val colorStops = arrayOf(
        0.2f to Color(0xFF0E0653),
        0.5f to Color(0xFF090341),
        1f to Color(0xFF070231)
    )
    SideEffect {
        systemUiController.setSystemBarsColor(Color(0xFF0E0653))
    }
    Column(modifier = Modifier
        .background(Brush.linearGradient(colorStops = colorStops))
        .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {


        Image(painter = painterResource(id = R.drawable.settings), contentDescription = "Settings",
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.4f)
                .padding(30.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "To enhance your app experience, the app requires the following permissions:",
                color = Color.White,
                fontFamily = FontFamily(Font(R.font.opensans_res)),
                fontWeight = FontWeight.ExtraBold,
                fontSize = 18.sp,
            )
        }
        if (!PermissionFunctions(context, context.packageName).isPackageUsageStatsPermissionEnabled()) {
            Spacer(modifier = Modifier.height(10.dp))
            permissionCard(
                "Usage access",
                "This permission is required to retrieve statistics about apps",
                navController,
                Screen.UsageAccess.route,
                Color(0xFFEE7848)
            )
        }
        else {
            Spacer(modifier = Modifier.height(10.dp))
            permissionCard(
                "Usage access",
                "This permission is required to retrieve statistics about apps",
                navController,
                Screen.UsageAccess.route,
                Color(0xFF00FF00)
            )
        }
        if (!PermissionFunctions(context, context.packageName).isAccessibilityServiceEnabled("MyAccessibilityService")) {
            Spacer(modifier = Modifier.height(15.dp))
            permissionCard(
                "Accessibility",
                "This permission is required to block apps and websites you select.",
                navController,
                Screen.Accessibility.route,
                Color(0xFFEE7848)
            )
        }
        else {
            Spacer(modifier = Modifier.height(15.dp))
            permissionCard(
                "Accessibility",
                "This permission is required to block apps and websites you select.",
                navController,
                Screen.Accessibility.route,
                Color(0xFF00FF00)
            )
        }
        Spacer(modifier = Modifier.height(15.dp))
        permissionCard("Display over other apps", "Since Android 10, this permission is required to block apps and websites.",navController, Screen.DisplayOverOtherApps.route, Color(0xFFEE7848))
        Spacer(modifier = Modifier.height(30.dp))

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun permissionCard(title: String, content: String, navController: NavController, navigationRoute : String, color : Color) {
    val openSans = FontFamily(
        Font(R.font.opensans_res),
    )

    Card(
        modifier = Modifier
            .height(95.dp)
            .fillMaxWidth(.96f),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = color),
        onClick = { navController.navigate(navigationRoute) }
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(0.97f),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = title, color = Color(0xFF000000),fontFamily = openSans, fontWeight = FontWeight.ExtraBold , modifier = Modifier
                    .padding(start = 10.dp)
                    .padding(top = 6.dp)
            )

            Icon(
                imageVector = Icons.Filled.ArrowForward,
                contentDescription = "go to settings",
                tint = Color(0xFF000000),
                modifier = Modifier
                    .size(25.dp)
                    .padding(top=1.dp),
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = content, color = Color(0xFF444343), fontFamily = openSans,modifier = Modifier.padding(start = 9.dp))
    }
}
