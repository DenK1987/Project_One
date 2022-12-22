package com.example.projectone.ui.addnote

import androidx.lifecycle.ViewModel
import com.example.projectone.models.Note
import com.example.projectone.repositories.NotesRepository

class AddNoteViewModel: ViewModel() {

    private val repository = NotesRepository()

    fun addNote(note: Note) {
        repository.addNote(note)
    }
}