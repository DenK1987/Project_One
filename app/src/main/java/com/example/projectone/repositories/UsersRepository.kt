package com.example.projectone.repositories

import com.example.projectone.db.DataBase
import com.example.projectone.models.User

class UsersRepository {

    fun addUser(user: User) {
        DataBase.db.userDao().addUser(user)
    }

    fun deleteUser(email: String) {
        DataBase.db.userDao().deleteUser(email)
    }

    fun getUser(email: String): User? {
        return DataBase.db.userDao().getUser(email)
    }
}