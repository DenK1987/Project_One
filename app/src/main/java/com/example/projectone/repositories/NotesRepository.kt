package com.example.projectone.repositories

import com.example.projectone.database.NotesDataBase
import com.example.projectone.model.Note

class NotesRepository {

    fun addNote(note: Note) {
        NotesDataBase.addNote(note)
    }

    fun deleteNote(note: Note) {
        NotesDataBase.deleteNote(note)
    }

    fun getListNotes(): List<Note> {
        return NotesDataBase.listNotes
    }
}