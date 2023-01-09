package com.example.projectone.repositories

import com.example.projectone.db.DataBase
import com.example.projectone.models.User

class UsersRepository {

    suspend fun addUser(user: User) {
        DataBase.db.userDao().addUser(user)
    }

    suspend fun deleteUser(email: String) {
        DataBase.db.userDao().deleteUser(email)
    }

    suspend fun getUser(email: String): User? {
        return DataBase.db.userDao().getUser(email)
    }
}