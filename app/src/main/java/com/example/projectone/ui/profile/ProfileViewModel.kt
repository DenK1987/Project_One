package com.example.projectone.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectone.repositories.NotesRepository
import com.example.projectone.repositories.SharedPreferencesRepository
import com.example.projectone.repositories.UserStatus
import com.example.projectone.repositories.UsersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val notesRepository: NotesRepository,
    private val usersRepository: UsersRepository,
    private val sharedPreferencesRepository: SharedPreferencesRepository
) : ViewModel() {

    fun deleteAllNotes(email: String) {
        viewModelScope.launch(Dispatchers.IO) {
            notesRepository.deleteAllNotesByUser(email)
        }
    }

    suspend fun getNotesCount(email: String): Int {
        return notesRepository.getNotesCountByUser(email)
    }

    fun deleteUser(email: String) {
        viewModelScope.launch(Dispatchers.IO) {
            usersRepository.deleteUser(email)
        }
    }

    fun getUserEmail() = sharedPreferencesRepository.getUserEmail()

    fun getUserFullName() = sharedPreferencesRepository.getUserFullName()

    fun setUserStatus(status: UserStatus) = sharedPreferencesRepository.setUserStatus(status)

    fun deleteUserProfile() = sharedPreferencesRepository.deleteUserProfile()
}