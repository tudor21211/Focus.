package com.example.focusparentapp
import com.example.focusparentapp.RoomDB.Entities.LocationCoordinatesEntity
import com.google.gson.Gson
import org.junit.Assert.assertEquals
import org.junit.Test

class LocationCoordinatesEntityTest {

    @Test
    fun testLocationCoordinatesEntityJsonMapping() {
        val json = """
            {
                "userId": "userTestId",
                "latitude": 37.7749,
                "longitude": -122.4194,
                "timestamp": "12/02/2021 12:00:00"
            }
        """.trimIndent()

        val gson = Gson()
        val entity = gson.fromJson(json, LocationCoordinatesEntity::class.java)
        assertEquals("userTestId", entity.userId)
        assertEquals(37.7749, entity.latitude, 0.0)
        assertEquals(-122.4194, entity.longitude, 0.0)
        assertEquals("12/02/2021 12:00:00", entity.timestamp)
    }
}