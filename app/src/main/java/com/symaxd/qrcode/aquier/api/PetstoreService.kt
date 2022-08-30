package com.symaxd.qrcode.aquier.api

import com.symaxd.qrcode.aquier.api.entities.ServerResponseBody
import com.symaxd.qrcode.aquier.api.entities.UserBody
import kotlinx.coroutines.Deferred
import retrofit2.http.*

interface PetstoreService {
    @GET("user/{username}")
    fun getUser(@Path("username") username: String): Deferred<UserBody>

    @POST("user")
    fun postUser(@Body userBody: UserBody): Deferred<ServerResponseBody>

    @DELETE("user/{username}")
    fun deleteUser(@Path("username") username: String): Deferred<ServerResponseBody>

}