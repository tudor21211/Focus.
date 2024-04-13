package com.example.focusparentapp.RoomDB.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.focusparentapp.RoomDB.DAO.PackagesDAO
import com.example.focusparentapp.RoomDB.Entities.PackageEntity
import kotlinx.coroutines.launch

class PackageViewModel (private val packagesDao : PackagesDAO) : ViewModel() {

    val allPackages: LiveData<List<PackageEntity>> = packagesDao.getAllPackages().asLiveData()

    fun insert(packageEntity: PackageEntity) = viewModelScope.launch {
        packagesDao.insert(packageEntity)
    }
}

