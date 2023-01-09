package com.example.projectone.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.projectone.models.User

@Dao
interface UserDao {

    @Insert
    suspend fun addUser(user: User)

    @Query("SELECT * FROM User WHERE email=(:email)")
    suspend fun getUser(email: String): User?

    @Query("DELETE FROM User WHERE email = (:email)")
    suspend fun deleteUser(email: String)
}