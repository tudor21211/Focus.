package com.example.focusparentapp
import com.example.focusparentapp.RoomDB.Entities.RestrictedKeywordsEntity
import com.google.gson.Gson
import org.junit.Assert.assertEquals
import org.junit.Test
class RestrictedKeywordsEntityTest {

    @Test
    fun testRestrictedKeywordsEntityJsonMapping() {
        val json = """
            {
                "restrictedKeyword": "example",
                "userId": "userTestId"
            }
        """.trimIndent()

        val gson = Gson()
        val entity = gson.fromJson(json, RestrictedKeywordsEntity::class.java)
        assertEquals("example", entity.restrictedKeyword)
        assertEquals("userTestId", entity.userId)
    }
}