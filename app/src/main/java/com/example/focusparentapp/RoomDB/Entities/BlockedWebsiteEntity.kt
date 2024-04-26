package com.example.focusparentapp.RoomDB.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "blockedWebsites")
data class BlockedWebsiteEntity(

    @PrimaryKey(autoGenerate = false)
    val websiteURL: String,
    val userId : String

)


@Entity(tableName = "restrictedKeywords")
data class RestrictedKeywordsEntity(
    @PrimaryKey(autoGenerate = false)
    val restrictedKeyword : String,
    val userId : String

)