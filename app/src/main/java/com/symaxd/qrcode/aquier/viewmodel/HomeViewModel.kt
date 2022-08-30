package com.symaxd.qrcode.aquier.viewmodel

import androidx.lifecycle.ViewModel
import com.symaxd.qrcode.aquier.repository.UserRepository
import javax.inject.Inject

class HomeViewModel @Inject constructor(val userRepository: UserRepository) : ViewModel() {
}