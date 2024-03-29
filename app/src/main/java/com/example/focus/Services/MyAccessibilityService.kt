package com.example.focus.Services

import android.accessibilityservice.AccessibilityService
import android.accessibilityservice.AccessibilityServiceInfo
import android.content.ContentValues.TAG
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.graphics.PixelFormat
import android.net.Uri
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
import androidx.compose.ui.text.toLowerCase
import com.example.focus.Data.RestrictedAppsManager
import com.example.focus.Data.RestrictedAppsManager.getRestrictedApps
import com.example.focus.Data.UserPreferences
import com.example.focus.Presentation.Screens.Landing.RestrictedAppView

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
        home.flags = FLAG_ACTIVITY_NEW_TASK
        startActivity(home)

    }


    @RequiresApi(Build.VERSION_CODES.R)
    override fun onAccessibilityEvent(event: AccessibilityEvent) {

        var packageName = event?.packageName.toString()
        var accessNodeInfo = event.source?.let { AccessibilityNodeInfo(it) }

        accessNodeInfo?.refresh()
        var idResourceName = accessNodeInfo?.viewIdResourceName



        if (packageName == "com.instagram.android" && RestrictedAppsManager.isInstagramRestricted()) {

            if (idResourceName == "com.instagram.android:id/scrubber" || idResourceName == "com.instagram.android:id/clips_viewer_view_pager") {

                val home = Intent(Intent.ACTION_MAIN)
                home.addCategory(Intent.CATEGORY_HOME)
                home.flags = FLAG_ACTIVITY_NEW_TASK
                startActivity(home)
            }

        }

        if (packageName == "com.google.android.youtube" && RestrictedAppsManager.isYoutubeRestricted()) {
            if (idResourceName == "com.google.android.youtube:id/reel_progress_bar" || idResourceName == "com.google.android.youtube:id/reel_recycler") {

                val home = Intent(Intent.ACTION_MAIN)
                home.addCategory(Intent.CATEGORY_HOME)
                home.flags = FLAG_ACTIVITY_NEW_TASK
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

        if (browserList.contains(packageName)) {
            try {
                if (AccessibilityEvent.eventTypeToString(event.eventType).contains("WINDOW")) {
                    var nodeEventInfo = event.source
                    if (nodeEventInfo != null) {
                        getAllWindowContent(nodeEventInfo)
                    }
                }
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }

        if (packageName == "com.android.chrome") {

            var findUrlBar =
                accessNodeInfo?.findAccessibilityNodeInfosByViewId("com.android.chrome:id/url_bar")
            if (!findUrlBar.isNullOrEmpty()) {
                val text = findUrlBar?.get(0)?.text.toString()
                try {
                    val restrictedUrl = RestrictedAppsManager.getRestrictedUrl()
                    for (t in restrictedUrl) if (text.contains(t)) {
                        val url = UserPreferences.getNavigateUrl()
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                        intent.flags = FLAG_ACTIVITY_NEW_TASK
                        startActivity(intent)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

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


    private val BROWSERS: String = "com.android.chrome"
    private val browserList: List<String> = BROWSERS.split(",\\s*")
    private var blacklistedKeywords = RestrictedAppsManager.getRestrictedKeywords()
    private fun getAllWindowContent(info: AccessibilityNodeInfo) {

        try {

            if (info == null) return;

            if (info.text != null && info.text.isNotEmpty()) {

                var capturedText = info.text.toString().toLowerCase()
                println("BLACKLIST IS : $blacklistedKeywords")
                for (word in blacklistedKeywords)
                    if (capturedText.contains(word)) {
                        val url = UserPreferences.getNavigateUrl()
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                        intent.flags = FLAG_ACTIVITY_NEW_TASK
                        startActivity(intent)
                }

            }
            if (info.childCount > 0) for (i in 0 until info.childCount) {
                var child = info.getChild(i)
                getAllWindowContent(child)
            }

        } catch (ex: Exception) {
            println("Exception caught in getUrls")
            ex.printStackTrace()
        }
    }


}