package com.example.focusparentapp.RoomDB.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.focusparentapp.RoomDB.Entities.PackageEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PackagesDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPackage(note: PackageEntity)

    @Update
    suspend fun updatePackage(note: PackageEntity)

    @Query("SELECT * FROM packages")
    fun getAllPackages(): Flow<List<PackageEntity>>

}