package com.example.focus.Presentation.Screens.MainPage

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi

class MainMenuActivity : ComponentActivity(){

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate (savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            AppNavigation()
        }
    }

}