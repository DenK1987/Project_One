package com.example.projectone.ui.searchnotes

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
class SearchViewModel @Inject constructor(
    private val repository: NotesRepository,
    private val sharedPreferencesRepository: SharedPreferencesRepository
) : ViewModel() {

    private var searchResult = arrayListOf<Note>()

    private val _listNotes = MutableLiveData<List<Note>>(searchResult)
    val listNotes: LiveData<List<Note>> = _listNotes

    fun getListNotes(userEmail: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val list = repository.getAllNotesByUser(userEmail)

            searchResult = ArrayList(list)
            _listNotes.postValue(searchResult)
        }
    }

    fun searchNotes(searchText: String, userEmail: String) {
        viewModelScope.launch(Dispatchers.IO) {
            searchResult = repository.getAllNotesByUser(userEmail).filter { note ->
                note.title.contains(searchText, ignoreCase = true) || note.message.contains(
                    searchText,
                    ignoreCase = true
                )
            } as ArrayList<Note>
            _listNotes.postValue(searchResult)
        }
    }

    fun getUserEmail() = sharedPreferencesRepository.getUserEmail()

    fun getListNotesSortedByTitle() {
        _listNotes.value = searchResult.sortedBy { note -> note.title }
    }

    fun getListNotesSortedByDateOfCreation() {
        _listNotes.value = searchResult.sortedByDescending { note -> note.dateOfCreation }
    }

    fun getListNotesSortedByScheduleDate() {
        _listNotes.value = searchResult.sortedByDescending { note -> note.scheduleDate }
    }
}