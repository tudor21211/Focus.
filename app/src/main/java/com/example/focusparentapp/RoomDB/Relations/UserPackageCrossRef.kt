package com.example.focusparentapp.RoomDB.Relations

import androidx.room.Entity
import androidx.room.ForeignKey
import com.example.focusparentapp.RoomDB.Entities.PackageEntity
import com.example.focusparentapp.RoomDB.Entities.UserEntity


@Entity(
    tableName = "user_packages",
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
        )
    ]
)
data class UserPackageCrossRef(
    val userId: String,
    val packageName: String
)