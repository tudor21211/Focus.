package com.example.listallapps.Receivers

import android.content.BroadcastReceiver
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.focus.Services.MyAccessibilityService
//import com.example.listallapps.Services.BlockingService


class RebootReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == Intent.ACTION_BOOT_COMPLETED) {
            val serviceIntent = Intent(context, MyAccessibilityService::class.java)// LA REBOOT SE RETRIMITE UN INTENT DE PORNIRE AL SERVICIULUI DE BLOCARE APLICATII
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                Log.d(TAG,"I REBOOTED DO SOMETHING")
                context?.startForegroundService(serviceIntent)
            } else {
                context?.startService(serviceIntent)
            }
        }
    }


}