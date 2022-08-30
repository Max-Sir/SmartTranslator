package com.symaxd.qrcode.aquier.utils

interface LoggerService {
    fun i(message: String)
    fun e(message: String)
    fun e(message: Throwable?)
}