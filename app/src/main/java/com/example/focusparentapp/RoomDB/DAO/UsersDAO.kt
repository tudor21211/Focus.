package com.example.focusparentapp.RoomDB.DAO

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.focusparentapp.RoomDB.Entities.BlockedWebsiteEntity
import com.example.focusparentapp.RoomDB.Entities.PackageEntity
import com.example.focusparentapp.RoomDB.Entities.RestrictedKeywordsEntity
import com.example.focusparentapp.RoomDB.Entities.UserEntity
import com.example.focusparentapp.RoomDB.Relations.UserPackageCrossRef
import com.example.focusparentapp.RoomDB.Relations.UserWithPackages
import com.example.focusparentapp.RoomDB.ViewModels.AppInfo
import com.example.focusparentapp.RoomDB.ViewModels.TimeSpentByUser
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


    @Query("SELECT \"appName\", packages.packageName AS \"packageName\", \"icon\" FROM packages INNER JOIN user_packages ON packages.packageName = user_packages.packageName WHERE user_packages.userId = :userId")
    fun getAppsInfoFromUser(userId: String) : List<AppInfo>

    @Query("SELECT \"deviceType\" FROM users WHERE userId = :userId")
    fun getDeviceType (userId: String) : String

    @Query("SELECT \"email\" FROM users WHERE userId = :userId")
    fun getUserEmail (userId: String) : String

    @Query("SELECT \"packageName\" , \"timeSpent\"  FROM user_packages WHERE userId = :userId")
    fun getTimeSpentByUser(userId: String) : Flow<List<TimeSpentByUser>>

    @Query("SELECT \"lastTimeUpdated\" FROM user_packages WHERE userId = :userId LIMIT 1")
    fun getLastTimeUpdated(userId : String) : Flow<String>

    @Query("UPDATE user_packages SET isBlocked=:isBlocked WHERE userId = :userId AND packageName = :packageName")
    suspend fun updateIsBlocked(userId : String, packageName : String, isBlocked : Boolean)

    @Query("SELECT isBlocked FROM user_packages WHERE userId = :userId AND packageName = :packageName")
    suspend fun getBlockedAppProperty(userId: String, packageName: String) : Boolean

    @Query("SELECT packageName from user_packages WHERE isBlocked = true AND userId=:userId ")
    suspend fun getAllBlockedApps(userId: String) : List<String>

    @Query("DELETE FROM blockedWebsites WHERE userId=:userId AND websiteURL=:websiteURL")
    suspend fun removeBlockedWebsite(userId : String, websiteURL : String)

    @Query("SELECT websiteURL from blockedWebsites WHERE userId = :userId")
    suspend fun getBlockedWebsites(userId: String) : List<String>

    @Query("DELETE FROM restrictedKeywords WHERE userId=:userId AND restrictedKeyword=:restrictedKeyword")
    suspend fun removeRestrictedKeyword(userId : String, restrictedKeyword : String)

    @Query("SELECT restrictedKeyword from restrictedKeywords WHERE userId = :userId")
    suspend fun getRestrictedKeywords(userId: String) : List<String>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertBlockedWebsite (note : BlockedWebsiteEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertRestrictedKeyword (note : RestrictedKeywordsEntity)

    @Query("SELECT websiteURL FROM blockedWebsites WHERE userId = :userId")
    fun getBlockedWebsitesAsFlow(userId: String): Flow<List<String>>

    @Query("SELECT restrictedKeyword FROM restrictedKeywords WHERE userId = :userId")
    fun getRestrictedKeywordsAsFlow(userId: String): Flow<List<String>>






    //UPDATES QUERIES
    @Query("UPDATE user_packages SET timeSpent = :newTimeSpent, lastTimeUpdated = :lastTimeUpdated WHERE userId = :userId AND packageName = :packageName")
    fun updateTimeSpent(userId: String, packageName: String, newTimeSpent: Long, lastTimeUpdated : String)

}