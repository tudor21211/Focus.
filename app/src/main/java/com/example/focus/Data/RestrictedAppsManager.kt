package com.example.focus.Data

object RestrictedAppsManager {

    private val listOfRestrictedApps =
        mutableListOf<String>() //ASTA POATE FI FACUTA CU ROOMS DATABASE SA RETIN APLICATIILE BLOCATE INTR-UN DB SI SA LE FAC ADD / REMOVE

    private val listOfRestrictedUrls =
        mutableListOf<String>()

    private val listOfRestrictedKeywords =
        mutableListOf<String>()

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

    fun addRestrictedUrl(url : String) {
        listOfRestrictedUrls.add(url)
    }

    fun removeRestrictedUrl(url:String)
    {
        listOfRestrictedUrls.remove(url)
    }

    fun getRestrictedUrl() : List<String> {
        return listOfRestrictedUrls
    }

    fun addRestrictedKeyword(keyword : String) {
        listOfRestrictedKeywords.add(keyword)
    }

    fun removeRestrictedKeyword(keyword:String)
    {
        listOfRestrictedKeywords.remove(keyword)
    }

    fun getRestrictedKeywords() : List<String> {
        return listOfRestrictedKeywords
    }
}