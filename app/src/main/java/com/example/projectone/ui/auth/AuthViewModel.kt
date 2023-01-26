package com.example.projectone.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectone.models.User
import com.example.projectone.repositories.SharedPreferencesRepository
import com.example.projectone.repositories.UserStatus
import com.example.projectone.repositories.UsersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: UsersRepository,
    private val sharedPreferencesRepository: SharedPreferencesRepository
) : ViewModel() {

    fun addUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(user)
        }
    }

    suspend fun getUser(email: String): User? {
        return repository.getUser(email)
    }

    fun setUserEmail(userEmail: String) = sharedPreferencesRepository.setUserEmail(userEmail)

    fun setUserStatus(status: UserStatus) = sharedPreferencesRepository.setUserStatus(status)

    fun setUserPassword(userPassword: String) =
        sharedPreferencesRepository.setUserPassword(userPassword)

    fun setUserFullName(userFirstName: String, userLastName: String) =
        sharedPreferencesRepository.setUserFullName(userFirstName, userLastName)
}