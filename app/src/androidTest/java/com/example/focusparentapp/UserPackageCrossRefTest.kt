package com.example.focusparentapp

import com.example.focusparentapp.RoomDB.Relations.UserPackageCrossRef
import com.google.gson.Gson
import org.junit.Assert
import org.junit.Test

class UserPackageCrossRefTest {

    @Test
    fun testUserPackageCrossRefEntityJsonMapping() {
        val json = """
            {
                "userId": "userTestId",
                "packageName": "com.example.app",
                "timeSpent": 3600,
                "isBlocked": false,
                "restrictionTime": 0,
                "lastTimeUpdated": "12/02/2021 12:00:00"
            }
        """.trimIndent()

        val gson = Gson()
        val entity = gson.fromJson(json, UserPackageCrossRef::class.java)
        Assert.assertEquals("userTestId", entity.userId)
        Assert.assertEquals("com.example.app", entity.packageName)
        Assert.assertEquals(3600, entity.timeSpent)
        Assert.assertEquals(false, entity.isBlocked)
        Assert.assertEquals(0, entity.restrictionTime)
        Assert.assertEquals("12/02/2021 12:00:00", entity.lastTimeUpdated)
    }
}