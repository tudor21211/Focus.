package com.example.focusparentapp.RoomDB.Entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "users", indices = [Index(value = ["userId"], unique = true)])
data class UserEntity(
    @PrimaryKey(autoGenerate = false)
    val userId : String,
    val email: String,
    val deviceType : String
)
