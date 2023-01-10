package com.example.projectone.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.projectone.models.Note

@Dao
interface NoteDao {

    @Insert
    suspend fun addNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    @Query("SELECT * FROM Note WHERE userEmail LIKE :email")
    suspend fun getAllNotesByUser(email: String): List<Note>

    @Query("DELETE FROM Note WHERE userEmail LIKE :email")
    suspend fun deleteAllNotesByUser(email: String)
}