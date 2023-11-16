package com.example.focus.Model.Permissions

import android.app.AppOpsManager
import android.app.admin.DeviceAdminReceiver
import android.app.admin.DevicePolicyManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import androidx.core.app.ComponentActivity

import androidx.core.content.ContextCompat.getSystemService

class PermissionFunctions (private var context: Context, private var packageName : String){


    fun isPackageUsageStatsPermissionEnabled(): Boolean {
        val appOpsManager = context.getSystemService(Context.APP_OPS_SERVICE) as AppOpsManager
        val mode = appOpsManager.checkOpNoThrow(AppOpsManager.OPSTR_GET_USAGE_STATS, android.os.Process.myUid(), packageName)
        return mode == AppOpsManager.MODE_ALLOWED
    }




}