package com.example.focusparentapp.RoomDB.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.focusparentapp.RoomDB.Entities.PackageEntity
import com.example.focusparentapp.RoomDB.Entities.UserEntity
import com.example.focusparentapp.RoomDB.Relations.UserWithPackages
import kotlinx.coroutines.flow.Flow

@Dao
interface UsersDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note: UserEntity)

    @Update
    suspend fun update(note: UserEntity)

    @Query("SELECT * FROM users")
    fun getAllUsers(): Flow<List<UserEntity>>

    @Transaction
    @Query("SELECT * FROM users WHERE userId = :userId")
    fun getUserWithPackages(userId : String) : Flow<List<UserWithPackages>>
}