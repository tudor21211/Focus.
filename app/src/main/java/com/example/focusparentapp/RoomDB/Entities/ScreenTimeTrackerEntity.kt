package com.example.focusparentapp.RoomDB.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "screen_time_tracker")
data class ScreenTimeTrackerEntity (
    @PrimaryKey(autoGenerate = false)
    val userId : String,
    val launchTracker : Int,
    val screenTime : String
    )