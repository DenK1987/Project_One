package com.example.projectone.ui.addnote

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
class AddNoteViewModel @Inject constructor(
    private val repository: NotesRepository,
    private val sharedPreferencesRepository: SharedPreferencesRepository
) : ViewModel() {

    fun addNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addNote(note)
        }
    }

    fun getUserEmail() = sharedPreferencesRepository.getUserEmail()
}