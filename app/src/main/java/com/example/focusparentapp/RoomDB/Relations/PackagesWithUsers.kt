package com.example.focusparentapp.RoomDB.Relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.focusparentapp.RoomDB.Entities.PackageEntity
import com.example.focusparentapp.RoomDB.Entities.UserEntity

data class PackagesWithUsers (
    @Embedded val packages : PackageEntity,
    @Relation(
        parentColumn = "packageName",
        entityColumn = "userId" ,
        associateBy = Junction(UserPackageCrossRef::class)
    )
    val users: List<UserEntity>
)