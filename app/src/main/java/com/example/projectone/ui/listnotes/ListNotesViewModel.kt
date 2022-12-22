package com.example.projectone.ui.listnotes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.projectone.models.Note
import com.example.projectone.repositories.NotesRepository

class ListNotesViewModel : ViewModel() {

    private val repository = NotesRepository()

    private val _listNotes = MutableLiveData<List<Note>>()
    val listNotes: LiveData<List<Note>> = _listNotes

    fun deleteNote(note: Note) {
        repository.deleteNote(note)
    }

    fun getListNotesByUser(email: String) {
        _listNotes.value = repository.getAllNotesByUser(email).sortedByDescending { note -> note.dateOfCreation }
    }
}