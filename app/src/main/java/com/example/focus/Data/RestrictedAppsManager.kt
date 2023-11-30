package com.example.focus.Data

object RestrictedAppsManager {
    private val listOfRestrictedApps =
        mutableListOf<String>() //ASTA POATE FI FACUTA CU ROOMS DATABASE SA RETIN APLICATIILE BLOCATE INTR-UN DB SI SA LE FAC ADD / REMOVE

    fun addRestrictedApp(packageName: String) {
        listOfRestrictedApps.add(packageName)
        println("LISTA APLICATII RESTRICTED: $listOfRestrictedApps")
    }

    fun removeRestrictedApp(packageName: String) {
        listOfRestrictedApps.remove(packageName)
    }

    fun getRestrictedApps(): List<String> {
        return listOfRestrictedApps
    }
}