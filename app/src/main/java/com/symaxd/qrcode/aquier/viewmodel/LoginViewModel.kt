package com.symaxd.qrcode.aquier.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.symaxd.qrcode.aquier.api.entities.UserBody
import com.symaxd.qrcode.aquier.repository.UserRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(val userRepository: UserRepository) : ViewModel() {

    val user by lazy { MutableLiveData<UserBody>() }

    val userNoFoundEvent by lazyOf(MutableLiveData(false))

    fun loginUser(username: String) {
        viewModelScope.launch {
            val userResponse = userRepository.getUser(username)
            userResponse?.let { user.postValue(it) } ?: userNoFoundEvent.postValue(true)
        }
    }

    fun endUserNoFoundEvent() {
        userNoFoundEvent.value = false
    }

}