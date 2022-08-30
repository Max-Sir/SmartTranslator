package com.symaxd.qrcode.aquier.data.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity("user_table")
@Parcelize
data class User(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id")
    val id: Long?,
    @ColumnInfo("username")
    val username: String?,
    @ColumnInfo("password")
    val password: String?,
    @ColumnInfo("phone")
    val phone: String?,
    @ColumnInfo("email")
    val email: String?,
    @ColumnInfo("firstName")
    val firstName: String?,
    @ColumnInfo("lastName")
    val lastName: String?,
    @ColumnInfo("userStatus")
    val userStatus: Long?
) : Parcelable