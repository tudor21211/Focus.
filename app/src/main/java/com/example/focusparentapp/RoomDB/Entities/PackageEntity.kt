package com.example.focusparentapp.RoomDB.Entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(tableName = "packages", indices = [Index(value = ["packageName"], unique = true)])
data class PackageEntity(
    @PrimaryKey(autoGenerate = false)
    val packageName: String,
    val appName: String,
    val icon : String,
)




