package com.example.focusparentapp.RoomDB.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "locationCoordinates")
data class LocationCoordinatesEntity (
    @PrimaryKey(autoGenerate = false)
    val userId : String,
    val longitude: Double,
    val latitude: Double,
    val timestamp: String = "Error getting timestamp"
)