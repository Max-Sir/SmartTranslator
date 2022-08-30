package com.symaxd.qrcode.aquier.repository

import android.content.Context
import com.symaxd.qrcode.aquier.api.PetstoreService
import com.symaxd.qrcode.aquier.api.entities.UserBody
import com.symaxd.qrcode.aquier.data.UserDao
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import timber.log.Timber
import javax.inject.Inject

/** @note this approach is bad, but for this moment and for diploma work it's will be normal*/
class UserRepositoryImpl @Inject constructor(
    val remoteDataSource: PetstoreService,
    val localDataSource: UserDao
) :
    UserRepository {


    @Inject
    lateinit var context: Context

    init {
        Timber.plant(Timber.DebugTree())
    }

    private val job = SupervisorJob()
    private val coroutineExceptionHandler: CoroutineExceptionHandler by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        CoroutineExceptionHandler { _, exception ->
            Timber.e("thrown an exception ${exception.suppressed.contentToString()}")
        }
    }

    override suspend fun saveUser(user: UserBody): Boolean =
        withContext(Dispatchers.IO + job + coroutineExceptionHandler) {
            val deferred = remoteDataSource.postUser(user)
            when (val code = deferred.await().code) {
                200 -> {
                    Timber.i("successfully saved")
                    true
                }
                else -> {
                    Timber.e("the code was: $code")
                    false
                }
            }
        }

    override suspend fun getUser(username: String): UserBody? =
        withContext(Dispatchers.IO + job + coroutineExceptionHandler) {
            val deferred = remoteDataSource.getUser(username)
            var userBody: UserBody? = null
            try {
                userBody = deferred.await()
            } catch (exc: HttpException) {
                Timber.e(exc)
            }
            userBody
        }

    override suspend fun deleteUser(username: String): Boolean =
        withContext(Dispatchers.IO + job + coroutineExceptionHandler) {
            val deferred = remoteDataSource.deleteUser(username)
            try {
                deferred.await()
                true
            } catch (exc: HttpException) {
                Timber.e(exc)
                false
            }
        }

}