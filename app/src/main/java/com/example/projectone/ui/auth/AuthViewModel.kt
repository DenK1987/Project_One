package com.example.projectone.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectone.models.User
import com.example.projectone.repositories.UsersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {

    private val repository = UsersRepository()

    fun addUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(user)
        }
    }

    suspend fun getUser(email: String): User? {
        return repository.getUser(email)
    }
}