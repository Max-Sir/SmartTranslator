package com.symaxd.qrcode.aquier.biometrics

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CiphertextWrapper(val ciphertext: ByteArray, val initializationVector: ByteArray) :
    Parcelable
