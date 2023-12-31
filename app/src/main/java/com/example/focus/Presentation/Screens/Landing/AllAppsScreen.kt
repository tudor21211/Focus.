package com.example.focus.Presentation.Screens.Landing

import android.app.usage.UsageStatsManager
import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.focus.Data.RestrictedAppsManager
import com.example.focus.Model.Permissions.GetAppsFunctions
import com.google.accompanist.drawablepainter.rememberDrawablePainter
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun allAppsScreen(context: Context, navController: NavController) {
    val systemUiController = rememberSystemUiController()

    SideEffect {
        systemUiController.setSystemBarsColor(Color(0xFF111416))
        systemUiController.setNavigationBarColor(Color.Black)
    }
    val appInfoList = remember {
        GetAppsFunctions(
            context.packageManager,
            context.getSystemService(Context.USAGE_STATS_SERVICE) as UsageStatsManager,
            context
        ).createAppList()
    }
    Column(
        Modifier
            .fillMaxSize()
            .background(Color(0xFF111416)),
    ) {
        Button(
            modifier = Modifier.padding(20.dp),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF323941)),
            onClick = { navController.navigate(Screen.TimeSpentScreen.route) }
        ) {
            Text(text = "Show time spent", color = Color.White)
        }
        ElevatedCard(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.elevatedCardColors(containerColor = Color(0xFF323941)),
        ) {
            LazyColumn(
                modifier = Modifier.padding(horizontal = 6.dp, vertical = 3.dp),
                content = {
                    items(appInfoList) { appInfo ->
                        val icon = appInfo.icon
                        val appName = appInfo.appName
                        val appPackageName = appInfo.appPackageName
                        val isAppRestricted = remember {
                            mutableStateOf(
                                appInfo.appPackageName in
                                        RestrictedAppsManager.getRestrictedApps()
                            )
                        }
                        OutlinedCard(
                            modifier = Modifier
                                .padding(3.dp),
                            colors =
                            CardDefaults.outlinedCardColors(
                                containerColor = Color(0xFF222429)
                            ),
                            shape = CardDefaults.elevatedShape,
                            border = BorderStroke(1.dp, Color(0xFF101312))
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(5.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 14.dp)
                            ) {
                                Image(
                                    painter = rememberDrawablePainter(icon),
                                    contentDescription = null,
                                    modifier = Modifier.size(45.dp)
                                )
                                Text(
                                    text = appName.toString(),
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White,
                                    fontSize = 15.sp,
                                    modifier = Modifier
                                        .padding(10.dp)

                                )

                                Spacer(modifier = Modifier.weight(1f))

                                IconButton(
                                    onClick = {
                                        if (isAppRestricted.value) {
                                            RestrictedAppsManager.removeRestrictedApp(
                                                appInfo.appPackageName
                                            )
                                        } else {
                                            RestrictedAppsManager.addRestrictedApp(
                                                appInfo.appPackageName
                                            )
                                        }
                                        isAppRestricted.value = !isAppRestricted.value
                                    },
                                    modifier = Modifier
                                        .width(71.dp)
                                        .height(70.dp)
                                ) {
                                    val icon =
                                        if (isAppRestricted.value) {
                                            Icons.Default.Done
                                        } else {
                                            Icons.Filled.Lock
                                        }

                                    Icon(
                                        icon,
                                        contentDescription =
                                        if (isAppRestricted.value) "UNBLOCK" else "BLOCK",
                                        Modifier.fillMaxSize(.8f),
                                        tint = Color.White
                                    )
                                }
                            }
                        }
                    }
                },
            )
        }
    }
}