package com.example.focusparentapp.Presentation.MainPage

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.focusparentapp.RoomDB.ViewModels.UsersViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun UserMenu(navController: NavController, userId : String, userViewModel: UsersViewModel){


    var deviceType by remember {
        mutableStateOf<String>("")
    }

    LaunchedEffect(Unit) {
        deviceType = withContext(Dispatchers.IO) {
            userViewModel.getDeviceType(userId)
        }
    }

    Text(text = deviceType)
    println("device type is $deviceType")

}