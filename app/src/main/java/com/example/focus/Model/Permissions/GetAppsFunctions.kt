package com.example.focus.Model.Permissions

import android.app.usage.UsageStatsManager
import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.os.Build
import androidx.annotation.RequiresApi
import java.time.Duration
import java.util.Calendar

class GetAppsFunctions {
    private var nonSystemApps : List<ApplicationInfo> = listOf()

    fun getNonSystemApps() : List<ApplicationInfo> {
        return nonSystemApps
    }

    fun getInstalledApps(packageManager: PackageManager) {
        var listInstalledApps: List<ApplicationInfo> = packageManager.getInstalledApplications(
            PackageManager.GET_ACTIVITIES
        )

        nonSystemApps = listInstalledApps.filter { appInfo ->
            (appInfo.flags and ApplicationInfo.FLAG_SYSTEM == 0)
        }
    }

    fun getTimeSpent(context: Context) : Map<String,Long> {

        val cal = Calendar.getInstance()
        cal.add(Calendar.DAY_OF_YEAR, -1) // 24 hours ago
        val endTime = System.currentTimeMillis()
        val startTime = cal.timeInMillis
        val mutableDict = mutableMapOf<String,Long>()

        val usm = context.getSystemService(Context.USAGE_STATS_SERVICE) as UsageStatsManager
        val queryUsageStats = usm.queryUsageStats(
            UsageStatsManager.INTERVAL_BEST,
            startTime,
            endTime
        )

        for (usageStats in queryUsageStats) {

            mutableDict[usageStats.packageName] = usageStats.totalTimeInForeground

        }

        return mutableDict
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
}