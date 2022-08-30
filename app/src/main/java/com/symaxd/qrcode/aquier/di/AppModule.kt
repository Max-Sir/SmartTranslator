package com.symaxd.qrcode.aquier.di

import android.content.Context
import com.symaxd.qrcode.aquier.api.PetstoreService
import com.symaxd.qrcode.aquier.data.UserDao
import com.symaxd.qrcode.aquier.di.entities.GoogleTranslator
import com.symaxd.qrcode.aquier.di.entities.TimberImpl
import com.symaxd.qrcode.aquier.repository.UserRepository
import com.symaxd.qrcode.aquier.repository.UserRepositoryImpl
import com.symaxd.qrcode.aquier.utils.LoggerService
import com.symaxd.qrcode.aquier.utils.TimberLoggerServiceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import timber.log.Timber
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module(includes = [NetworkModule::class])
object AppModule {
    @Provides
    @Singleton
    @GoogleTranslator
    fun x(): String = "LINK"

    @Provides
    @Singleton
    fun timberTree(): Timber.Tree = Timber.DebugTree()

    @Provides
    @Singleton
    @TimberImpl
    fun logger(tree: Timber.Tree): LoggerService = TimberLoggerServiceImpl(tree)

    @Provides
    @Singleton
    fun repository(
        @ApplicationContext context: Context,
        petstoreService: PetstoreService,
        localDataSource: UserDao
    ): UserRepository = UserRepositoryImpl(petstoreService, localDataSource)

}