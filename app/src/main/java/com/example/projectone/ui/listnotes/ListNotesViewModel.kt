package com.example.projectone.ui.listnotes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectone.models.Note
import com.example.projectone.repositories.NotesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListNotesViewModel : ViewModel() {

    private val repository = NotesRepository()

    private val _listNotes = MutableLiveData<List<Note>>()
    val listNotes: LiveData<List<Note>> = _listNotes

    fun deleteNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteNote(note)
        }
    }

    fun getListNotesByUser(email: String) {
        viewModelScope.launch {
            _listNotes.value = repository.getAllNotesByUser(email).sortedByDescending { note -> note.dateOfCreation }
        }
    }
}