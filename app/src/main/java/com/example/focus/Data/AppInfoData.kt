package com.example.focus.Data

import android.graphics.drawable.Drawable

data class AppInfoData(
    val icon: Drawable,
    val appName: CharSequence,
    val timeSpent: String,
    val timeSpentLong: Long,
    val packageName: String
)

data class AppInfoDataNoTime(
    val icon: Drawable,
    val appName: CharSequence,
    val appPackageName: String
)


