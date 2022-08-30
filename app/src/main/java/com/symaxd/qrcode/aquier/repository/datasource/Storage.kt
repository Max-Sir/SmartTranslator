package com.symaxd.qrcode.aquier.repository.datasource

import com.symaxd.qrcode.aquier.api.entities.UserBody

interface UserStorage {
    fun save(value: UserBody)
    fun get(): UserBody
}