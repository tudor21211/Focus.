package com.example.focusparentapp.RoomDB.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.example.focusparentapp.RoomDB.DAO.UsersDAO
import com.example.focusparentapp.RoomDB.Entities.PackageEntity
import com.example.focusparentapp.RoomDB.Entities.UserEntity
import com.example.focusparentapp.RoomDB.Relations.UserPackageCrossRef
import com.example.focusparentapp.RoomDB.Relations.UserWithPackages
import kotlinx.coroutines.flow.Flow

class UsersViewModel (private val userDao: UsersDAO) {

    suspend fun insertUserAndPackages(user: UserEntity, packages: List<PackageEntity>) {
        // Insert user into users table
        userDao.insertUser(user)

        // Insert packages into packages table
        packages.forEach { packageEntity ->
            userDao.insertPackage(packageEntity)
        }

        // Create user-package relationships and insert into user_packages table
        val userPackages = packages.map { packageEntity ->
            UserPackageCrossRef(user.userId, packageEntity.packageName)
        }
        userDao.insertUserPackages(userPackages)
    }

    suspend fun insertUser(user: UserEntity) {
        userDao.insertUser(user)
    }

    suspend fun insertPackage(packageEntity: PackageEntity) {
        userDao.insertPackage(packageEntity)
    }

    fun getAllUsers(): Flow<List<UserEntity>> {
        return userDao.getAllUsers()
    }

    fun getAllPackages(): Flow<List<PackageEntity>> {
        return userDao.getAllPackages()
    }

    suspend fun updateUser(user: UserEntity) {
        userDao.updateUser(user)
    }

   suspend fun  getUserWithPackages(userId : String) : LiveData<List<UserWithPackages>> {
       return userDao.getUserWithPackages(userId).asLiveData()
   }

}