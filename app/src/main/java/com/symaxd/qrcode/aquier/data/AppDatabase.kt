package com.symaxd.qrcode.aquier.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.symaxd.qrcode.aquier.data.entities.User

@Database(entities = [User::class], exportSchema = true, version = 0)
abstract class AppDatabase : RoomDatabase() {

    abstract val userDao: UserDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context,
                        AppDatabase::class.java,
                        "qr_translator_database"
                    )
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }


}