package com.example.focusparentapp

import com.example.focusparentapp.RoomDB.Entities.PackageEntity
import com.google.gson.Gson
import org.junit.Assert.assertEquals
import org.junit.Test

class PackageEntityTest {

    @Test
    fun testPackageEntityJsonMapping() {
        val json = """
            {
                "packageName": "com.example.app",
                "appName": "Example App",
                "icon": "icon_string"
            }
        """.trimIndent()

        val gson = Gson()
        val entity = gson.fromJson(json, PackageEntity::class.java)
        assertEquals("com.example.app", entity.packageName)
        assertEquals("Example App", entity.appName)
        assertEquals("icon_string", entity.icon)
    }
}