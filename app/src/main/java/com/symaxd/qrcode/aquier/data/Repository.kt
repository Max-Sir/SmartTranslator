package com.symaxd.qrcode.aquier.data

import android.content.Context
import android.content.SharedPreferences

interface Repository {
    fun <T> saveToPreferences(data: T)
    fun <T> loadFromPreferences(key: String): T
}