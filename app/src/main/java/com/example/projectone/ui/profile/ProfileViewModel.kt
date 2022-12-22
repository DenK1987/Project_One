package com.example.projectone.ui.profile

import androidx.lifecycle.ViewModel
import com.example.projectone.repositories.NotesRepository
import com.example.projectone.repositories.UsersRepository

class ProfileViewModel : ViewModel() {

    private val notesRepository = NotesRepository()

    private val usersRepository = UsersRepository()

    fun deleteAllNotes(email: String) {
        notesRepository.deleteAllNotesByUser(email)
    }

    fun getNotesCount(email: String): Int {
        return notesRepository.getNotesCountByUser(email)
    }

    fun deleteUser(email: String) {
        usersRepository.deleteUser(email)
    }
}