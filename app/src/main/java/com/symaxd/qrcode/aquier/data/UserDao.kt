package com.symaxd.qrcode.aquier.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.symaxd.qrcode.aquier.data.entities.User

@Dao
interface UserDao {
    @Insert
    fun saveUser(user: User)

    @Query(value = "DELETE FROM user_table WHERE username = :username ")
    fun deleteUser(username: String)

    @Query("DELETE FROM user_table")
    fun deleteAll()

    @Query("SELECT * FROM user_table WHERE username = :username")
    suspend fun getUser(username: String): User
}