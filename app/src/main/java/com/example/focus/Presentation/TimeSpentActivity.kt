package com.example.focus.Presentation

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.focus.Data.AppInfoData
import com.example.focus.Model.Permissions.GetAppsFunctions
import com.example.focus.ui.theme.FocusTheme
import com.google.accompanist.drawablepainter.rememberDrawablePainter

class TimeSpentActivity() : ComponentActivity(){


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FocusTheme {
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    timeSpentScreen(packageManager ,this)
                }

            }
        }
    }



    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    fun timeSpentScreen(
        packageManager : PackageManager,
        context : Context
    ) {

        val myApps = GetAppsFunctions()
        myApps.getInstalledApps(packageManager)
        val nonSystemApps = myApps.getNonSystemApps()

        val nameTimeMap = myApps.getTimeSpent(context)

        val appInfoList = nonSystemApps.map { appInfo ->
            val icon: Drawable = packageManager.getApplicationIcon(appInfo.packageName)
            val appName: CharSequence = packageManager.getApplicationLabel(appInfo)
            val timeSpent: String =
                GetAppsFunctions().formatMilliseconds(nameTimeMap[appInfo.packageName] ?: 0)

            val timeSpentLong : Long = GetAppsFunctions().formatMillisecondsLong(nameTimeMap[appInfo.packageName] ?: 0)
            val packageName = appInfo.packageName
            AppInfoData(icon, appName, timeSpent, timeSpentLong, packageName)
        }

        Column(Modifier.fillMaxSize()) {

            Row() {
                Button(
                    modifier = Modifier.padding(20.dp),
                    shape = CircleShape,
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Cyan),
                    onClick = { reload() }) {
                    Text(text = "Reload")
                }


                Button(
                    modifier = Modifier.padding(20.dp),
                    shape = CircleShape,
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Cyan),
                    onClick = { goToAllApps() }) {
                    Text(text = "Main Screen")
                }
            }
            ElevatedCard(
                modifier = Modifier
                    .fillMaxWidth(),
                colors = CardDefaults.elevatedCardColors(
                  containerColor = Color(0xFF323941)
                ),


            ) {
                LazyColumn(
                modifier = Modifier.padding(horizontal = 6.dp, vertical = 3.dp),
                    content = {
                        itemsIndexed(appInfoList) { index, appInfo ->
                            val icon = appInfo.icon
                            val appName = appInfo.appName
                            val timeSpent = appInfo.timeSpent
                            val timeSpentLong = appInfo.timeSpentLong
                            val packageName = appInfo.packageName


                            //println("IN FUNCTION TIME SPENT $timeSpent")
                            if (timeSpent != "0 h : 0 m : 0 s") {
                                OutlinedCard(
                                    modifier = Modifier
                                        .padding(3.dp),
                                    colors = CardDefaults.outlinedCardColors(
                                        containerColor = Color(0xFF222429)
                                    ),
                                    shape = CardDefaults.elevatedShape,
                                    border = BorderStroke(1.dp,Color(0xFF101312))
                                ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(5.dp),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp)
                                )
                                {

                                    Image(
                                        painter = rememberDrawablePainter(icon),
                                        contentDescription = null,
                                        modifier = Modifier.size(50.dp)
                                    )
                                    Text(
                                        text = appName.toString(),
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 15.sp,
                                        modifier = Modifier
                                            .padding(10.dp)
                                    )

                                    Spacer(modifier = Modifier.weight(1f))
                                    Text(text = appInfo.timeSpent)
                                }
                            }
                        }
                      }
                    },
                )

            }
        }
    }

    private fun goToAllApps() {
        val sendIntent = Intent(this, GetAllAppsActivity::class.java)
        startActivity(sendIntent)
    }

    private fun reload() {
        val sendIntent = Intent(this, TimeSpentActivity::class.java)
        startActivity(sendIntent)
    }


}