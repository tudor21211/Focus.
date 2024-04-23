package com.example.focusparentapp.RoomDB.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.focusparentapp.RoomDB.Entities.PackageEntity
import com.example.focusparentapp.RoomDB.Entities.UserEntity
import com.example.focusparentapp.RoomDB.Relations.UserPackageCrossRef
import com.example.focusparentapp.RoomDB.Relations.UserWithPackages
import kotlinx.coroutines.flow.Flow

@Dao
interface UsersDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(note: UserEntity)

    @Update
    suspend fun updateUser(note: UserEntity)

    @Query("SELECT * FROM users")
    fun getAllUsers(): Flow<List<UserEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPackage(note: PackageEntity)
    @Update
    suspend fun updatePackage(note: PackageEntity)

    @Query("SELECT * FROM packages")
    fun getAllPackages(): Flow<List<PackageEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUserPackages(userPackages: List<UserPackageCrossRef>)

    @Transaction
    @Query("SELECT * FROM users WHERE userId = :userId")
    fun getUserWithPackages(userId: String): Flow<List<UserWithPackages>>


    @Query("SELECT \"timeSpent\" FROM user_packages WHERE userId = :userId")
    fun getTimeSpentByUser(userId: String) : List<Long>

    @Query("SELECT \"icon\" FROM packages INNER JOIN user_packages ON packages.packageName = user_packages.packageName WHERE user_packages.userId = :userId")
    fun getIconsFromUser(userId: String) : List<String>

    @Query("SELECT \"deviceType\" FROM users WHERE userId = :userId")
    fun getDeviceType (userId: String) : String




}