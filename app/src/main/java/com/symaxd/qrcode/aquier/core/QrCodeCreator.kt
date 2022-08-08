package com.symaxd.qrcode.aquier.core

import android.graphics.Bitmap
import android.graphics.Color
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.qrcode.QRCodeWriter

object QrCodeCreator {

    private fun getQrCodeBitmap(password: String): Bitmap {
        val size = 512 //pixels
        val qrCodeContent: String = password

        /** Parameters: needed to enale UTF-8 charset*/
        val hints = hashMapOf<EncodeHintType, Any>().also {
            it[EncodeHintType.MARGIN] = 1 // Make the QR code buffer border narrower
            it[EncodeHintType.CHARACTER_SET] = "UTF-8"
        }

        val bits = QRCodeWriter().encode(qrCodeContent, BarcodeFormat.QR_CODE, size, size,hints)
        return Bitmap.createBitmap(size, size, Bitmap.Config.RGB_565).also {
            for (x in 0 until size) {
                for (y in 0 until size) {
                    it.setPixel(x, y, if (bits[x, y]) Color.BLACK else Color.WHITE)
                }
            }
        }
    }

    fun create(password: String): Bitmap = getQrCodeBitmap(password)
}