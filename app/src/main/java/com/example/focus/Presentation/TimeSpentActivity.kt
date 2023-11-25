package com.example.focus.Presentation




import android.app.usage.UsageStatsManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

class TimeSpentActivity() : ComponentActivity(){


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FocusTheme {
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    timeSpentScreen(packageManager , 0)
                }

            }
        }
    }



    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    fun timeSpentScreen(
        packageManager : PackageManager,
        timeInterval : Int
    ) {



        val myApps = GetAppsFunctions(this.packageManager, this.getSystemService(Context.USAGE_STATS_SERVICE) as UsageStatsManager, this)

        myApps.getInstalledApps()
        var nonSystemApps = myApps.getNonSystemApps()

        val nameTimeMap = myApps.getTimeSpent(timeInterval)


        var appInfoList = nonSystemApps.map { appInfo ->
            val icon: Drawable = packageManager.getApplicationIcon(appInfo.packageName)
            val appName: CharSequence = packageManager.getApplicationLabel(appInfo)
            val timeSpent: String =
                myApps.formatMilliseconds(nameTimeMap[appInfo.packageName] ?: 0)

            val timeSpentLong : Long = myApps.formatMillisecondsLong(nameTimeMap[appInfo.packageName] ?: 0)
            val packageName = appInfo.packageName
            AppInfoData(icon, appName, timeSpent, timeSpentLong, packageName)
        }.sortedByDescending { it.timeSpentLong }



        Column(Modifier.fillMaxSize()) {



            Row(modifier = Modifier.fillMaxWidth()) {
                IconButton(
                    modifier = Modifier
                        .padding(20.dp)
                        .size(30.dp),
                    onClick = { goToAllApps() }
                ) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = "Go back")
                }
                Spacer(modifier = Modifier.weight(1f))
                IconButton(
                    modifier = Modifier
                        .padding(20.dp)
                        .size(30.dp),
                    onClick = { reload() }
                ) {
                    Icon(Icons.Filled.Refresh, contentDescription = "Reload")
                }

            }

            var appInfoList by remember { mutableStateOf(appInfoList) }

            dropdownMenu(
                appInfoList,
                myApps,
                onAppInfoListUpdated = { updatedList ->
                    appInfoList = updatedList
                }
            )


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
        finish()
        startActivity(sendIntent)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun dropdownMenu(
        appInfoList: List<AppInfoData>,
        myApps: GetAppsFunctions,
        onAppInfoListUpdated: (List<AppInfoData>) -> Unit
    ){

        var expanded by remember { mutableStateOf(false) }
        val timeInterval = arrayOf("1 day", "3 days", "7 days", "15 days", "1 month")
        var selectedText by remember { mutableStateOf(timeInterval[0]) }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(3.dp)
        ) {
            Row{
                Spacer(modifier = Modifier.weight(1f))
                ExposedDropdownMenuBox(
                    modifier = Modifier.fillMaxWidth(.5f),
                    expanded = expanded,
                    onExpandedChange = {
                        expanded = !expanded
                    }
                ) {
                    TextField(
                        value = selectedText,
                        onValueChange = {},
                        readOnly = true,
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                        modifier = Modifier.menuAnchor()
                    )

                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        timeInterval.forEach { item ->
                            DropdownMenuItem(
                                text = { Text(text = item) },
                                onClick = {

                                    selectedText = item
                                    expanded = false

                                    val days = when (item) {
                                        "1 day" -> 0
                                        "3 days" -> 3
                                        "7 days" -> 7
                                        "15 days" -> 15
                                        "1 month" -> 30
                                        else -> 1
                                    }

                                    val nameTimeMap = myApps.getTimeSpent(days)
                                    val updatedAppInfoList = appInfoList.map { appInfo ->
                                        val timeSpent = myApps.formatMilliseconds(nameTimeMap[appInfo.packageName] ?: 0)
                                        val timeSpentLong = myApps.formatMillisecondsLong(nameTimeMap[appInfo.packageName] ?: 0)

                                        appInfo.copy(
                                            timeSpent = timeSpent,
                                            timeSpentLong = timeSpentLong
                                        )
                                    }.sortedByDescending{ it.timeSpentLong}

                                    // Update the UI with the new list of apps
                                    onAppInfoListUpdated(updatedAppInfoList)

                                }
                            )
                        }
                    }
                }
            }
        }
    }

}