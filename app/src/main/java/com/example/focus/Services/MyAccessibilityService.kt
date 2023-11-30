package com.example.focus.Services

import android.accessibilityservice.AccessibilityService
import android.accessibilityservice.AccessibilityServiceInfo
import android.content.ContentValues.TAG
import android.content.Intent
import android.graphics.PixelFormat
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.Gravity
import android.view.WindowManager
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED
import android.view.accessibility.AccessibilityNodeInfo
import androidx.annotation.RequiresApi
import com.example.focus.Data.RestrictedAppsManager.getRestrictedApps
import com.example.focus.Presentation.RestrictedAppView

class MyAccessibilityService : AccessibilityService() {
    private var windowManager: WindowManager? = null
    private var restrictedView: RestrictedAppView? = null
    private var isOverlayShown = false
    private var delayedHandler: Handler? = null
    private val removeViewRunnable = Runnable {

        removeRestrictedView()
        isOverlayShown = false
        val home = Intent(Intent.ACTION_MAIN)
        home.addCategory(Intent.CATEGORY_HOME)
        home.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(home)

    }

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onAccessibilityEvent(event: AccessibilityEvent) {

        var packageName = event.packageName.toString()
        var accessNodeInfo = event.source?.let { AccessibilityNodeInfo(it) }
        accessNodeInfo?.refresh()
        var idResourceName = accessNodeInfo?.viewIdResourceName

        println("resource name is $idResourceName")
        if (packageName == "com.instagram.android") {
            if (idResourceName == "com.instagram.android:id/scrubber" || idResourceName == "com.instagram.android:id/clips_viewer_view_pager") {

                val home = Intent(Intent.ACTION_MAIN)
                home.addCategory(Intent.CATEGORY_HOME)
                home.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(home)

            }
        }

        if (packageName == "com.google.android.youtube") {
            if (idResourceName == "com.google.android.youtube:id/reel_progress_bar" || idResourceName == "com.google.android.youtube:id/reel_recycler") {


                val home = Intent(Intent.ACTION_MAIN)
                home.addCategory(Intent.CATEGORY_HOME)
                home.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(home)

            }
        }

        if (packageName in getRestrictedApps() && !isOverlayShown) {
            showRestrictedView()
            isOverlayShown = true

            // Schedule a delayed action to remove the restricted view after 3 seconds
            delayedHandler = Handler(Looper.getMainLooper())
            delayedHandler?.postDelayed(removeViewRunnable, 3000)
        }

        // Remove the restricted view immediately if the user leaves the app
        if (isOverlayShown && packageName == "com.android.launcher") {
            removeViewImmediately()
        }
    }

    private fun removeViewImmediately() {
        delayedHandler?.removeCallbacks(removeViewRunnable)
        removeRestrictedView()
        isOverlayShown = false
    }

    private fun removeRestrictedView() {
        windowManager?.removeView(restrictedView)
        isOverlayShown = false
        restrictedView = null
        delayedHandler = null
    }


    private fun showRestrictedView() {
        try {
            windowManager = getSystemService(WINDOW_SERVICE) as WindowManager?
            println("INTRAT AICI")
            val params = WindowManager.LayoutParams(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT
            )
            params.gravity = Gravity.CENTER

            restrictedView = RestrictedAppView(this)
            windowManager?.addView(restrictedView, params)

        } catch (e: Exception) {
            Log.e("ShowRestrictedView", "Exception for restricted view: $e")
            e.printStackTrace()
        }
    }

    override fun onInterrupt() {
        Log.e(TAG, "Something went wrong on Interrupt")
    }

    private fun getAllVisibleText(rootNode: AccessibilityNodeInfo): List<String> {
        val visibleText = mutableListOf<String>()

        if (rootNode.isVisibleToUser) {
            // Check if the current node is visible to the user
            if (rootNode.text != null) {
                visibleText.add(rootNode.text.toString())
            }
        }

        for (i in 0 until rootNode.childCount) {
            val childNode = rootNode.getChild(i)
            if (childNode != null) {
                visibleText.addAll(getAllVisibleText(childNode))
            }
        }

        return visibleText
    }

    override fun onServiceConnected() {
        super.onServiceConnected()

        var info = AccessibilityServiceInfo()

        info.apply {
            eventTypes = AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED or TYPE_WINDOW_CONTENT_CHANGED

            flags = AccessibilityServiceInfo.FLAG_REPORT_VIEW_IDS

            feedbackType = AccessibilityServiceInfo.FEEDBACK_SPOKEN

            notificationTimeout = 100
        }
        Log.e(TAG, "on service connected: ")
        this.serviceInfo = info

    }

}