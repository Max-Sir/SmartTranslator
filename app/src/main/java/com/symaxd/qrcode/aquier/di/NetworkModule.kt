package com.symaxd.qrcode.aquier.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.symaxd.qrcode.aquier.api.PetstoreService
import com.symaxd.qrcode.aquier.di.entities.HeadersInterceptor
import com.symaxd.qrcode.aquier.di.entities.PetstoreURL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {
    @Provides
    @Singleton
    @PetstoreURL
    fun petstoreUrl(): String = "https://petstore.swagger.io/v2/"

    @Provides
    @Singleton
    fun converterFactory(): Converter.Factory = GsonConverterFactory.create()

    @Provides
    @Singleton
    fun loggingIntercepter(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().setLevel(level = HttpLoggingInterceptor.Level.BODY)

    @Provides
    @Singleton
    @HeadersInterceptor
    fun custmLoggingIntercptor(): Interceptor = Interceptor() { chain ->
        chain.request().newBuilder().addHeader("Accept", "application/json")
            .addHeader("Content-Type", "application/json").addHeader("Platform", "android")
            .build().let { chain.proceed(it) }
    }

    @Provides
    @Singleton
    fun client(
        interceptor: HttpLoggingInterceptor,
        @HeadersInterceptor customInterceptor: Interceptor
    ): OkHttpClient =
        OkHttpClient.Builder().addInterceptor(interceptor).addInterceptor(customInterceptor).build()

    @Provides
    @Singleton
    fun callAdapterFactory(): CoroutineCallAdapterFactory = CoroutineCallAdapterFactory()

    @Provides
    @Singleton
    fun petstoreService(
        @PetstoreURL url: String,
        client: OkHttpClient,
        converterFactory: Converter.Factory,
        coroutineCallAdapterFactory: CoroutineCallAdapterFactory
    ): PetstoreService =
        Retrofit.Builder().baseUrl(url).addConverterFactory(converterFactory).client(client)
            .addCallAdapterFactory(coroutineCallAdapterFactory).build()
            .create(PetstoreService::class.java)

}