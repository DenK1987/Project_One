package com.example.projectone.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectone.repositories.NotesRepository
import com.example.projectone.repositories.UsersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {

    private val notesRepository = NotesRepository()

    private val usersRepository = UsersRepository()

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
}