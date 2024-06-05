package com.example.focusparentapp
import com.example.focusparentapp.RoomDB.Entities.UserEntity
import com.google.gson.Gson
import org.junit.Assert.assertEquals
import org.junit.Test
class UserEntityTest {

    @Test
    fun testUserEntityJsonMapping() {
        val json = """
            {
                "userId": "userTestId",
                "email": "test@test.com",
                "deviceType": "Android"
            }
        """.trimIndent()

        val gson = Gson()
        val entity = gson.fromJson(json, UserEntity::class.java)
        assertEquals("userTestId", entity.userId)
        assertEquals("test@test.com", entity.email)
        assertEquals("Android", entity.deviceType)
    }
}