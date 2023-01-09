package com.example.projectone.repositories

import com.example.projectone.db.DataBase
import com.example.projectone.models.Note

class NotesRepository {

    suspend fun addNote(note: Note) {
        DataBase.db.noteDao().addNote(note)
    }

    suspend fun deleteNote(note: Note) {
        DataBase.db.noteDao().deleteNote(note)
    }

    suspend fun getAllNotesByUser(email: String): List<Note> {
        return DataBase.db.noteDao().getAllNotesByUser(email)
    }

    suspend fun getNotesCountByUser(email: String): Int {
        return DataBase.db.noteDao().getAllNotesByUser(email).size
    }

    suspend fun deleteAllNotesByUser(email: String) {
        DataBase.db.noteDao().deleteAllNotesByUser(email)
    }
}