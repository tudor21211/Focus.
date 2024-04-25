package com.example.focusparentapp.Navigation

sealed class Screens (val route : String) {

    object LandingScreen : Screens(route = "landingScreen")
    object MainPage : Screens(route = "mainPage")
    object TutorialPager : Screens(route = "tutorialPager")
    object Setup : Screens(route = "setup")
    object DeviceBound : Screens(route = "deviceBound")
    object UserMenu : Screens(route = "userMenu/{userId}")
    object AppsScreen : Screens(route = "appsScreen/{userId}")

    object TestScreen : Screens(route = "testScreen")
}