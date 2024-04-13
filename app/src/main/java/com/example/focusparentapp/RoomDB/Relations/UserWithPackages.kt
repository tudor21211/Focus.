package com.example.focusparentapp.RoomDB.Relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.focusparentapp.RoomDB.Entities.PackageEntity
import com.example.focusparentapp.RoomDB.Entities.UserEntity

data class UserWithPackages (
    @Embedded val user: UserEntity,
    @Relation(
        parentColumn = "userId",
        entityColumn = "userId"
    )
    val packages: List<PackageEntity>
)
