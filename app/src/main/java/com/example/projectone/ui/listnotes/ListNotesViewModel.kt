package com.example.projectone.ui.listnotes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectone.models.Note
import com.example.projectone.repositories.NotesRepository
import com.example.projectone.repositories.SharedPreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListNotesViewModel @Inject constructor(
    private val repository: NotesRepository,
    private val sharedPreferencesRepository: SharedPreferencesRepository
) : ViewModel() {

    private val _listNotes = MutableLiveData<List<Note>>()
    val listNotes: LiveData<List<Note>> = _listNotes

    fun deleteNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteNote(note)
        }
    }

    fun getListNotesByUser(email: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _listNotes.postValue(
                repository.getAllNotesByUser(email)
                    .sortedByDescending { note -> note.dateOfCreation })
        }
    }

    fun getUserEmail() = sharedPreferencesRepository.getUserEmail()
}