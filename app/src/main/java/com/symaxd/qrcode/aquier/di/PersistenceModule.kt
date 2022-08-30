package com.symaxd.qrcode.aquier.di

import android.content.Context
import com.symaxd.qrcode.aquier.data.AppDatabase
import com.symaxd.qrcode.aquier.data.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object PersistenceModule {
    @Provides
    @Singleton
    fun appDataBase(@ApplicationContext context: Context): AppDatabase =
        AppDatabase.getInstance(context)

    @Provides
    @Singleton
    fun useDao(appDatabase: AppDatabase): UserDao = appDatabase.userDao
}