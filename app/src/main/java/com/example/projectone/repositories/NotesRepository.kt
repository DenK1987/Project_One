package com.example.projectone.repositories

import com.example.projectone.db.DataBase
import com.example.projectone.models.Note

class NotesRepository {

    fun addNote(note: Note) {
        DataBase.db.noteDao().addNote(note)
    }

    fun deleteNote(note: Note) {
        DataBase.db.noteDao().deleteNote(note)
    }

    fun getAllNotesByUser(email: String): List<Note> {
        return DataBase.db.noteDao().getAllNotesByUser(email)
    }

    fun getNotesCountByUser(email: String): Int {
        return DataBase.db.noteDao().getAllNotesByUser(email).size
    }

    fun deleteAllNotesByUser(email: String) {
        DataBase.db.noteDao().deleteAllNotesByUser(email)
    }
}