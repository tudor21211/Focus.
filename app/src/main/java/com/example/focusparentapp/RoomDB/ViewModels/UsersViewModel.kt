package com.example.focusparentapp.RoomDB.ViewModels

import android.graphics.drawable.Drawable
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.focusparentapp.RoomDB.DAO.UsersDAO
import com.example.focusparentapp.RoomDB.Entities.BlockedWebsiteEntity
import com.example.focusparentapp.RoomDB.Entities.PackageEntity
import com.example.focusparentapp.RoomDB.Entities.RestrictedKeywordsEntity
import com.example.focusparentapp.RoomDB.Entities.UserEntity
import com.example.focusparentapp.RoomDB.Relations.UserPackageCrossRef
import com.example.focusparentapp.RoomDB.Relations.UserWithPackages
import com.example.focusparentapp.Utils.Utils
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class UsersViewModel (private val userDao: UsersDAO) : ViewModel() {

    suspend fun insertUserAndPackages(user: UserEntity, packages: List<PackageEntity>, timeSpent: List<Long>) {
        // Insert user into users table
        userDao.insertUser(user)

        // Insert packages into packages table
        packages.forEach { packageEntity ->
            userDao.insertPackage(packageEntity)
        }
        val userPackages = mutableListOf<UserPackageCrossRef>()
        // Create user-package relationships and insert into user_packages table
        packages.forEachIndexed { index, packageEntity ->
            val userPackageCrossRef = UserPackageCrossRef(user.userId, packageEntity.packageName, timeSpent[index])
            userPackages.add(userPackageCrossRef)
        }
        userDao.insertUserPackages(userPackages)
    }

    suspend fun insertUser(user: UserEntity) {
        userDao.insertUser(user)
    }



    fun getAllUsers(): Flow<List<UserEntity>> {
        return userDao.getAllUsers()
    }



    suspend fun updateUser(user: UserEntity) {
        userDao.updateUser(user)
    }

    suspend fun getAppsInfoFromUser(userId: String) : List<AppInfo>{
        return userDao.getAppsInfoFromUser(userId).map {
            AppInfo(it.appName, it.packageName, it.icon)
        }
    }

   suspend fun getUserWithPackages(userId : String) : LiveData<List<UserWithPackages>> {
       return userDao.getUserWithPackages(userId).asLiveData()
   }

    suspend fun getDeviceType(userId : String) : String {
        return userDao.getDeviceType(userId)
    }

    suspend fun getUserEmail(userId : String) : String {
        return userDao.getUserEmail(userId)
    }
    suspend fun updateIsBlocked(userId : String, packageName : String, isBlocked : Boolean){
        return userDao.updateIsBlocked(userId, packageName, isBlocked)
    }

    suspend fun getBlockedAppProperty(userId: String, packageName: String) : Boolean{
        return userDao.getBlockedAppProperty(userId,packageName)
    }

    suspend fun getAllBlockedApps (userId : String) : List<String> {
        return userDao.getAllBlockedApps(userId)
    }

    suspend fun removeBlockedWebsite(userId: String, websiteURL : String){
        userDao.removeBlockedWebsite(userId, websiteURL)
    }

    suspend fun insertBlockedWebsite(blockedWebsiteEntity: BlockedWebsiteEntity) = viewModelScope.launch {
        userDao.insertBlockedWebsite(blockedWebsiteEntity)
    }
    suspend fun getBlockedWebsites(userId: String) : List<String>{
        return userDao.getBlockedWebsites(userId)
    }

    suspend fun removeRestrictedKeyword(userId: String, restrictedKeyword : String){
        userDao.removeRestrictedKeyword(userId, restrictedKeyword)
    }

    suspend fun getRestrictedKeywords(userId: String) : List<String>{
        return userDao.getRestrictedKeywords(userId)
    }

    suspend fun insertRestrictedKeyword(restrictedKeywordsEntity: RestrictedKeywordsEntity) = viewModelScope.launch {
        userDao.insertRestrictedKeyword(restrictedKeywordsEntity)
    }


    fun getBlockedWebsitesAsFlow(userId: String): Flow<List<String>> {
        return userDao.getBlockedWebsitesAsFlow(userId)
    }

    fun getRestrictedKeywordsAsFlow(userId: String): Flow<List<String>> {
        return userDao.getRestrictedKeywordsAsFlow(userId)
    }

    fun getTimeSpentByUser(userId : String) : Flow<List<TimeSpentByUser>> {
        return userDao.getTimeSpentByUser(userId)
    }

    fun updateTimeSpent(userId: String, packageName: String, newTimeSpent: Long, lastTimeUpdated : String) {
        userDao.updateTimeSpent(userId, packageName, newTimeSpent, lastTimeUpdated)
    }

    fun getLastTimeUpdated(userId: String) : Flow<String> {
        return userDao.getLastTimeUpdated(userId)
    }

}

data class AppInfo(val appName: String, val packageName: String, val icon : String)

data class TimeSpentByUser(
    val packageName: String,
    val timeSpent: Long
)
