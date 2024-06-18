package com.example.focusparentapp.RoomDB.ViewModels

import androidx.lifecycle.ViewModel
import com.example.focusparentapp.RoomDB.DAO.UsersDAO
import com.example.focusparentapp.RoomDB.Entities.PackageEntity
import kotlinx.coroutines.flow.Flow

class PackagesViewModel (private val userDao: UsersDAO) : ViewModel() {

    suspend fun insertPackage(packageEntity: PackageEntity) {
        userDao.insertPackage(packageEntity)
    }

    fun getAllPackages(): Flow<List<PackageEntity>> {
        return userDao.getAllPackages()
    }


}