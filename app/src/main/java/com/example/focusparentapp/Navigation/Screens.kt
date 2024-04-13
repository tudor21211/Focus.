package com.example.focusparentapp.Navigation

sealed class Screens (val route : String) {

    object LandingScreen : Screens(route = "landingScreen")

}