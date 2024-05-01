package com.example.focusparentapp.Utils

import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.Base64

class Utils {
    fun byteStringToDrawable(byteString: String): Drawable {
        val byteArray = Base64.decode(byteString, Base64.DEFAULT)
        val bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
        return BitmapDrawable(null, bitmap)
    }

    object TimeUtils {
        fun convertMillisecondsToTime(milliseconds: Long): String {
            val seconds = (milliseconds / 1000) % 60
            val minutes = (milliseconds / (1000 * 60)) % 60
            val hours = (milliseconds / (1000 * 60 * 60)) % 24
            return String.format("%02d:%02d:%02d", hours, minutes, seconds)
        }
    }
}