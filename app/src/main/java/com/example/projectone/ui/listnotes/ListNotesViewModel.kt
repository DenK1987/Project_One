package com.example.projectone.ui.listnotes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.projectone.model.Note
import com.example.projectone.repositories.NotesRepository

class ListNotesViewModel : ViewModel() {

    private val repository = NotesRepository()

    val listNotes = MutableLiveData<List<Note>>()

    fun addNote(note: Note) {
        repository.addNote(note)
    }

    fun deleteNote(note: Note) {
        repository.deleteNote(note)
    }

    fun getListNotes() {
        listNotes.value = repository.getListNotes()
    }
}