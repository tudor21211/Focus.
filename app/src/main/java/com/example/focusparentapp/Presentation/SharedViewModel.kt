package com.example.focusparentapp.Presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class SharedViewModel : ViewModel() {

    private val _userNumber = MutableStateFlow<Int?>(null)
    val userNumber: StateFlow<Int?> = _userNumber.asStateFlow()

    private val _imageResId = MutableStateFlow<Int?>(null)
    val imageResId: StateFlow<Int?> = _imageResId.asStateFlow()

    fun setUserDetails(userNumber: Int, imageResId: Int) {
        _userNumber.value = userNumber
        _imageResId.value = imageResId
    }
}