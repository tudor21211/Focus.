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
}