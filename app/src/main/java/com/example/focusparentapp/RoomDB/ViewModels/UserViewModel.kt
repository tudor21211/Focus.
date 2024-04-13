package com.example.focusparentapp.RoomDB.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.focusparentapp.RoomDB.DAO.PackagesDAO
import com.example.focusparentapp.RoomDB.DAO.UsersDAO
import com.example.focusparentapp.RoomDB.Entities.PackageEntity
import com.example.focusparentapp.RoomDB.Entities.UserEntity
import com.example.focusparentapp.RoomDB.Relations.UserWithPackages
import kotlinx.coroutines.launch

class UserViewModel (private val userDAO : UsersDAO) : ViewModel() {

    val allUsers: LiveData<List<UserEntity>> = userDAO.getAllUsers().asLiveData()

    fun insert(userEntity: UserEntity) = viewModelScope.launch {
        userDAO.insert(userEntity)
    }

    suspend fun getUserWithPackages(userId: String): LiveData<List<UserWithPackages>> {
        return userDAO.getUserWithPackages(userId).asLiveData()
    }
}