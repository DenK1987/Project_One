package com.example.projectone.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.projectone.models.Note

@Dao
interface NoteDao {

    @Insert
    fun addNote(note: Note)

    @Delete
    fun deleteNote(note: Note)

    @Query("SELECT * FROM Note WHERE userEmail LIKE :email")
    fun getAllNotesByUser(email: String): List<Note>

    @Query("DELETE FROM Note WHERE userEmail LIKE :email")
    fun deleteAllNotesByUser(email: String)



    @Query("DELETE FROM Note")
    fun deleteAllNotes()

    @Query("SELECT * FROM Note")
    fun getAllNotes(): List<Note>
}