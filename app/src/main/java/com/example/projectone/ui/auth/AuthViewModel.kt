package com.example.projectone.ui.auth

import androidx.lifecycle.ViewModel
import com.example.projectone.models.User
import com.example.projectone.repositories.UsersRepository

class AuthViewModel : ViewModel() {

    private val repository = UsersRepository()

    fun addUser(user: User) {
        repository.addUser(user)
    }

    fun getUser(email: String): User? {
        return repository.getUser(email)
    }
}