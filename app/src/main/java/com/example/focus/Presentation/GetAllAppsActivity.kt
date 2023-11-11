package com.example.focus.Presentation

import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.focus.Data.AppInfoDataNoTime
import com.example.focus.Data.RestrictedAppsManager
import com.example.focus.Model.Permissions.GetAppsFunctions
import com.example.focus.Model.Permissions.PermissionFunctions
import com.example.focus.ui.theme.FocusTheme
import com.google.accompanist.drawablepainter.rememberDrawablePainter

class GetAllAppsActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (!PermissionFunctions(this, packageName).isPackageUsageStatsPermissionEnabled()) {
            startActivity(Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS))
        }
        setContent {
            FocusTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    getAllAppsScreen(packageManager)
                }
            }
        }
    }

    @Composable
    fun getAllAppsScreen(packageManager: PackageManager) {
        val myApps = GetAppsFunctions()
        myApps.getInstalledApps(packageManager)
        val nonSystemApps = myApps.getNonSystemApps()

        val appInfoList = remember {
            nonSystemApps.map { appInfo ->

                val icon: Drawable = packageManager.getApplicationIcon(appInfo.packageName)
                val appName: CharSequence = packageManager.getApplicationLabel(appInfo)
                val appPackageName: String = appInfo.packageName

                AppInfoDataNoTime(icon, appName, appPackageName)
            }
        }

        Column(Modifier.fillMaxSize()) {
            Button(
                modifier = Modifier.padding(20.dp),
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(containerColor = Color.Cyan),
                onClick = { goToTimeSpent() }
            ) {
                Text(text = "Show time spent")
            }
            ElevatedCard(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.elevatedCardColors(containerColor = Color(0xFF323941)),
            ) {
                LazyColumn(
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
                                modifier = Modifier.fillMaxHeight(0.1f).padding(3.dp),
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
                                    modifier = Modifier.fillMaxWidth().padding(8.dp)
                                ) {
                                    Image(
                                        painter = rememberDrawablePainter(icon),
                                        contentDescription = null,
                                        modifier = Modifier.size(50.dp)
                                    )
                                    Text(
                                        text = appName.toString(),
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 15.sp,
                                        modifier =
                                        Modifier
                                            // .fillMaxWidth()
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
                                        modifier = Modifier.width(70.dp).height(70.dp)
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
                                            Modifier.width(60.dp).height(60.dp)
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

    private fun goToTimeSpent() {
        val sendIntent = Intent(this, TimeSpentActivity::class.java)
        startActivity(sendIntent)
    }
}
