package com.example.projectone.database

import com.example.projectone.model.Note

object NotesDataBase {

    private val _listNotes: MutableList<Note> = mutableListOf()
    val listNotes: List<Note> = _listNotes

    fun addNote(note: Note) {
        _listNotes.add(note)
    }

    fun deleteNote(note: Note) {
        _listNotes.remove(note)
    }

}