package com.example.focusparentapp

import com.example.focusparentapp.RoomDB.Entities.PackageStatsEntity
import com.google.gson.Gson
import org.junit.Assert.assertEquals
import org.junit.Test

class PackageStatsEntityTest {

    @Test
    fun testPackageStatsEntityJsonMapping() {
        val json = """
            {
                "packageName": "com.example.app",
                "oneDay": 3600,
                "threeDays": 10800,
                "oneWeek": 25200,
                "oneMonth": 108000
            }
        """.trimIndent()

        val gson = Gson()
        val entity = gson.fromJson(json, PackageStatsEntity::class.java)
        assertEquals("com.example.app", entity.packageName)
        assertEquals(3600, entity.oneDay)
        assertEquals(10800, entity.threeDays)
        assertEquals(25200, entity.oneWeek)
        assertEquals(108000, entity.oneMonth)
    }
}