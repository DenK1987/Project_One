package com.example.projectone.repositories

import com.example.projectone.db.UserDao
import com.example.projectone.models.User
import javax.inject.Inject

class UsersRepository @Inject constructor(
    private val userDao: UserDao
) {

    suspend fun addUser(user: User) {
        userDao.addUser(user)
    }

    suspend fun deleteUser(email: String) {
        userDao.deleteUser(email)
    }

    suspend fun getUser(email: String): User? {
        return userDao.getUser(email)
    }
}