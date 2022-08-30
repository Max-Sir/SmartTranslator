package com.symaxd.qrcode.aquier.repository

import com.symaxd.qrcode.aquier.api.entities.UserBody

interface UserRepository {
    suspend fun saveUser(user: UserBody): Boolean
    suspend fun getUser(username: String): UserBody?
    suspend fun deleteUser(username: String): Boolean
}