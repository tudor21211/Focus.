package com.example.focus.Model.Permissions

import android.app.usage.UsageStatsManager
import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.example.focus.Data.AppInfoData
import com.example.focus.Data.AppInfoDataNoTime
import java.text.SimpleDateFormat
import java.time.Duration
import java.util.Calendar

class GetAppsFunctions (
    private val packageManager: PackageManager,
    private val usageStatsManager: UsageStatsManager,
    private val context: Context
){
    private var nonSystemApps : List<ApplicationInfo> = listOf()

    fun getNonSystemApps() : List<ApplicationInfo> {
        return nonSystemApps
    }

    fun getInstalledApps() {
        var listInstalledApps: List<ApplicationInfo> = packageManager.getInstalledApplications(
            PackageManager.GET_ACTIVITIES
        )

        nonSystemApps = listInstalledApps.filter { appInfo ->
            (appInfo.flags and ApplicationInfo.FLAG_SYSTEM == 0)
        }
    }


     fun getTimeSpent(periodOfTimeInDays : Int): Map<String, Long> {
        val cal = Calendar.getInstance()

        cal.add(Calendar.DAY_OF_YEAR,-periodOfTimeInDays)
        cal.set(Calendar.HOUR_OF_DAY, 0)
        cal.set(Calendar.MINUTE, 0)
        cal.set(Calendar.SECOND, 0)
        cal.set(Calendar.MILLISECOND , 0)

        val startTime = cal.timeInMillis

        cal.timeInMillis = System.currentTimeMillis()
        val endTime = cal.timeInMillis
        val mutableDict = mutableMapOf<String, Long>()

        Log.d("Debug", "Start Time: ${SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(startTime)}")
        Log.d("Debug", "End Time: ${SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(endTime)}")
        Log.d("Debug", cal.toString())

        val usm = context.getSystemService(Context.USAGE_STATS_SERVICE) as UsageStatsManager


            val queryUsageStats = usm.queryAndAggregateUsageStats(
                startTime,
                endTime
            )

            for ((packageName, usageStats) in queryUsageStats) {
                mutableDict[packageName] = usageStats.totalTimeInForeground
            }

        return  mutableDict

    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun formatMilliseconds(milliseconds: Long): String {
        val duration = Duration.ofMillis(milliseconds)
        val hours = duration.toHours()
        val minutes = duration.minusHours(hours).toMinutes()
        val seconds = duration.minusHours(hours).minusMinutes(minutes).seconds
        return "$hours h : $minutes m : $seconds s"
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun formatMillisecondsLong(milliseconds: Long): Long {
        val duration = Duration.ofMillis(milliseconds)
        return duration.toMillis()
    }

    @Composable
    fun createAppList () : List<AppInfoDataNoTime> {

        val myApps = GetAppsFunctions(context.packageManager, context.getSystemService(Context.USAGE_STATS_SERVICE) as UsageStatsManager, context)
        myApps.getInstalledApps()
        val nonSystemApps = myApps.getNonSystemApps()

        for (name in nonSystemApps) {
            println("NAME OF THE APPS ${name.packageName}")
        }


        val appInfoList = remember {
            nonSystemApps.map { appInfo ->

                val icon: Drawable = packageManager.getApplicationIcon(appInfo.packageName)
                val appName: CharSequence = packageManager.getApplicationLabel(appInfo)
                val appPackageName: String = appInfo.packageName

                AppInfoDataNoTime(icon, appName, appPackageName)
            }
        }

        return appInfoList
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    fun createAppListWithTimeSpent (timeInterval : Int) : List <AppInfoData>  {
        val myApps = GetAppsFunctions(context.packageManager, context.getSystemService(Context.USAGE_STATS_SERVICE) as UsageStatsManager, context)

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

        return appInfoList
    }


}