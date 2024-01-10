package com.example.focus.Model.Permissions

import android.app.usage.UsageStatsManager
import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.focus.Data.AppInfoData
import com.example.focus.Data.AppInfoDataNoTime
import java.text.SimpleDateFormat
import java.time.Duration
import java.util.Calendar
import java.util.concurrent.TimeUnit

class GetAppsFunctions(
    private val packageManager: PackageManager,
    private val usageStatsManager: UsageStatsManager,
    private val context: Context
) {
    private var nonSystemApps: List<ApplicationInfo> = listOf()

    private fun getNonSystemApps(): List<ApplicationInfo> {
        return nonSystemApps
    }

    private fun getInstalledApps() {
        var listInstalledApps: List<ApplicationInfo> = packageManager.getInstalledApplications(
            PackageManager.GET_ACTIVITIES
        )

        nonSystemApps = listInstalledApps.filter { appInfo ->
            (appInfo.flags and ApplicationInfo.FLAG_SYSTEM == 0) || (appInfo.packageName == "com.google.android.youtube") || (appInfo.packageName == "com.android.chrome")
        }
    }


    fun getTimeSpent(periodOfTimeInDays: Int): Map<String, Long> {
        val cal = Calendar.getInstance()

        cal.add(Calendar.DAY_OF_YEAR, -periodOfTimeInDays)
        cal.set(Calendar.HOUR_OF_DAY, 0)
        cal.set(Calendar.MINUTE, 0)
        cal.set(Calendar.SECOND, 0)
        cal.set(Calendar.MILLISECOND, 0)

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

        return mutableDict

    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun formatMillisecondsWithoutSeconds(milliseconds: Long): String {
        val duration = Duration.ofMillis(milliseconds)
        val hours = duration.toHours()
        val minutes = duration.minusHours(hours).toMinutes()
        val seconds = duration.minusHours(hours).minusMinutes(minutes).seconds
        if (hours==0L && minutes==0L) {
            return "$seconds s"
        }
        return "$hours h : $minutes m"
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun formatMillisecondsLong(milliseconds: Long): Long {
        val duration = Duration.ofMillis(milliseconds)
        return duration.toMillis()
    }


    fun createAppList(): List<AppInfoDataNoTime> {

        val myApps = GetAppsFunctions(
            context.packageManager,
            context.getSystemService(Context.USAGE_STATS_SERVICE) as UsageStatsManager,
            context
        )
        myApps.getInstalledApps()
        val nonSystemApps = myApps.getNonSystemApps()

        for (name in nonSystemApps) {
            println("NAME OF THE APPS ${name.packageName}")
        }


        val appInfoList = nonSystemApps.map { appInfo ->

            val icon: Drawable = packageManager.getApplicationIcon(appInfo.packageName)
            val appName: CharSequence = packageManager.getApplicationLabel(appInfo)
            val appPackageName: String = appInfo.packageName

            AppInfoDataNoTime(icon, appName, appPackageName)
        }


        return appInfoList
    }

    @RequiresApi(Build.VERSION_CODES.O)

    fun createAppListWithTimeSpent(timeInterval: Int): List<AppInfoData> {
        val myApps = GetAppsFunctions(
            context.packageManager,
            context.getSystemService(Context.USAGE_STATS_SERVICE) as UsageStatsManager,
            context
        )

        myApps.getInstalledApps()
        var nonSystemApps = myApps.getNonSystemApps()

        val nameTimeMap = myApps.getTimeSpent(timeInterval)


        var appInfoList = nonSystemApps.map { appInfo ->
            val icon: Drawable = packageManager.getApplicationIcon(appInfo.packageName)
            val appName: CharSequence = packageManager.getApplicationLabel(appInfo)
            val timeSpent: String =
                myApps.formatMillisecondsWithoutSeconds(nameTimeMap[appInfo.packageName] ?: 0)

            val timeSpentLong: Long =
                myApps.formatMillisecondsLong(nameTimeMap[appInfo.packageName] ?: 0)
            val packageName = appInfo.packageName
            AppInfoData(icon, appName, timeSpent, timeSpentLong, packageName)
        }.sortedByDescending { it.timeSpentLong }

        return appInfoList
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getTotalTimeSpent(map: Map<String, Long>) : String {

        var totalTimeSpent = 0L
        for (key in map.keys) {
            totalTimeSpent += map[key] ?: 0
        }
        return formatMillisecondsWithoutSeconds(totalTimeSpent)

    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getTotalTimeSpentAverage(map: Map<String, Long>) : Long{

        var totalTimeSpent = 0L
        for (key in map.keys) {
            totalTimeSpent += map[key] ?: 0
        }
        totalTimeSpent /= 7

        return totalTimeSpent

    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getTopFiveMostUsedApps(): List<AppInfoData> {
        val myApps = GetAppsFunctions(
            context.packageManager,
            context.getSystemService(Context.USAGE_STATS_SERVICE) as UsageStatsManager,
            context
        )
        myApps.getInstalledApps()
        val nonSystemApps = myApps.getNonSystemApps()

        val nameTimeMap = myApps.getTimeSpent(7)

        val appInfoList = nonSystemApps.map { appInfo ->
            val icon: Drawable = packageManager.getApplicationIcon(appInfo.packageName)
            val appName: CharSequence = packageManager.getApplicationLabel(appInfo)
            val timeSpent: String =
                myApps.formatMillisecondsWithoutSeconds(nameTimeMap[appInfo.packageName] ?: 0)
            val timeSpentLong: Long =
                myApps.formatMillisecondsLong(nameTimeMap[appInfo.packageName] ?: 0)
            val packageName = appInfo.packageName
            AppInfoData(icon, appName, timeSpent, timeSpentLong, packageName)
        }

        return appInfoList.sortedByDescending { it.timeSpentLong }.take(5)
    }



}