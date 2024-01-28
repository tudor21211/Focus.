package com.example.focus.Presentation.Screens.Landing

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import android.view.MotionEvent
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView

@SuppressLint("ResourceType")
class RestrictedAppView(context: Context) : FrameLayout(context) {

    init {
        layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        setBackgroundColor(Color.parseColor("#85000000"))

        val textView = TextView(context)
        textView.text = "Access restricted \n\n This app was blocked and can't be accessed"
        textView.setTextColor(Color.WHITE)
        textView.textSize = 24f
        textView.gravity = Gravity.CENTER
        textView.setTypeface(null, Typeface.BOLD)

        val imageView = ImageView(context)

        val params = LayoutParams(
            LayoutParams.WRAP_CONTENT,
            LayoutParams.WRAP_CONTENT
        )
        params.gravity = Gravity.CENTER
        addView(textView, params)
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        // Doesn't allow system events to be processed
        return true
    }

}
