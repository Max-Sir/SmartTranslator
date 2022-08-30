package com.symaxd.qrcode.aquier.api.entities


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ServerResponseBody(
    @SerializedName("code")
    val code: Int?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("message")
    val message: String?
) : Parcelable