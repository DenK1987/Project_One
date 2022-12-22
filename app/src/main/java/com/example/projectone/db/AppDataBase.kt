package com.example.projectone.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.projectone.models.Note
import com.example.projectone.models.User

@Database(entities = [Note::class, User::class], version = 1)
abstract class AppDataBase : RoomDatabase() {

    abstract fun noteDao(): NoteDao

    abstract fun userDao(): UserDao
}