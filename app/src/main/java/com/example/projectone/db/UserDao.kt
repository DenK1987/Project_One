package com.example.projectone.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.projectone.models.User

@Dao
interface UserDao {

    @Insert
    fun addUser(user: User)

    @Query("SELECT * FROM User WHERE email=(:email)")
    fun getUser(email: String): User?

    @Query("DELETE FROM User WHERE email = (:email)")
    fun deleteUser(email: String)



    @Query("DELETE FROM User")
    fun deleteAllUsers()

    @Query("SELECT * FROM User")
    fun getAllUsers(): List<User>
}