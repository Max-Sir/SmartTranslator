package com.symaxd.qrcode.aquier.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.symaxd.qrcode.aquier.api.entities.UserBody
import com.symaxd.qrcode.aquier.repository.UserRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class RegistrationViewModel @Inject constructor(val userRepository: UserRepository) : ViewModel() {

    fun signUpUser(userBody: UserBody) {
        viewModelScope.launch {
            userRepository.saveUser(userBody)
        }
    }
}