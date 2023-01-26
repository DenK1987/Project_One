package com.example.projectone.repositories

import com.example.projectone.db.NoteDao
import com.example.projectone.models.Note
import javax.inject.Inject

class NotesRepository @Inject constructor(
    private val noteDao: NoteDao
) {

    suspend fun addNote(note: Note) {
        noteDao.addNote(note)
    }

    suspend fun deleteNote(note: Note) {
        noteDao.deleteNote(note)
    }

    suspend fun getAllNotesByUser(email: String): List<Note> {
        return noteDao.getAllNotesByUser(email)
    }

    suspend fun getNotesCountByUser(email: String): Int {
        return noteDao.getAllNotesByUser(email).size
    }

    suspend fun deleteAllNotesByUser(email: String) {
        noteDao.deleteAllNotesByUser(email)
    }
}