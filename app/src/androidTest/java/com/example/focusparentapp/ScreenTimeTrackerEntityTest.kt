package com.example.focusparentapp
import com.example.focusparentapp.RoomDB.Entities.ScreenTimeTrackerEntity
import com.google.gson.Gson
import org.junit.Assert.assertEquals
import org.junit.Test
class ScreenTimeTrackerEntityTest {

    @Test
    fun testScreenTimeTrackerEntityJsonMapping() {
        val json = """
            {
                "userId": "userTestId",
                "launchTracker": 7200,
                "screenTime": 15
            }
        """.trimIndent()

        val gson = Gson()
        val entity = gson.fromJson(json, ScreenTimeTrackerEntity::class.java)
        assertEquals("userTestId", entity.userId)
        assertEquals(7200, entity.launchTracker)
        assertEquals("15", entity.screenTime)
    }
}