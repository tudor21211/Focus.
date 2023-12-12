package com.example.focus.Model.Permissions

import android.app.AppOpsManager
import android.content.Context
import android.provider.Settings

class PermissionFunctions(private var context: Context, private var packageName: String) {


    fun isPackageUsageStatsPermissionEnabled(): Boolean {
        val appOpsManager = context.getSystemService(Context.APP_OPS_SERVICE) as AppOpsManager
        val mode = appOpsManager.checkOpNoThrow(
            AppOpsManager.OPSTR_GET_USAGE_STATS,
            android.os.Process.myUid(),
            packageName
        )
        return mode == AppOpsManager.MODE_ALLOWED
    }

    fun isAccessibilityServiceEnabled(accessibilityService: String): Boolean {
        val enabledServicesSetting = Settings.Secure.getString(
            context.contentResolver,
            Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES
        )
        return enabledServicesSetting?.contains(accessibilityService) == true
    }

    fun isOverlayPermissionEnabled(): Boolean {
        return Settings.canDrawOverlays(context)
    }


    fun areAllPermissionsEnabled(): Boolean {
        return isAccessibilityServiceEnabled("MyAccessibilityService") &&
                isPackageUsageStatsPermissionEnabled() &&
                isOverlayPermissionEnabled()
    }


}