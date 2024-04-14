package com.example.focusparentapp.RoomDB.ViewModels

import com.example.focusparentapp.RoomDB.DAO.UsersDAO
import com.example.focusparentapp.RoomDB.Entities.PackageEntity
import com.example.focusparentapp.RoomDB.Entities.UserEntity
import com.example.focusparentapp.RoomDB.Relations.UserPackageCrossRef

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

    fun getAllUsers() {
        userDao.getAllUsers()
    }

    fun getAllPackages() {
        userDao.getAllPackages()
    }

    suspend fun updateUser(user: UserEntity) {
        userDao.updateUser(user)
    }
}