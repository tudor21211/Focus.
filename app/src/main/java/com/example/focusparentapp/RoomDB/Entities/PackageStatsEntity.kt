package com.example.focusparentapp.RoomDB.Entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "packages_stats",
    primaryKeys = ["userId", "packageName"],
    foreignKeys = [
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = ["userId"],
            childColumns = ["userId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = PackageEntity::class,
            parentColumns = ["packageName"],
            childColumns = ["packageName"],
            onDelete = ForeignKey.CASCADE
        )]
)
data class PackageStatsEntity(
    val userId : String,
    val packageName : String,
    val oneDay : Long,
    val threeDays : Long,
    val oneWeek : Long,
    val oneMonth : Long
)
