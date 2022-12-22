package com.example.projectone.db

import android.content.Context
import androidx.room.Room

object DataBase {

    lateinit var db: AppDataBase

    fun initDB(context: Context) {
        db = Room.databaseBuilder(
            context,
            AppDataBase::class.java, "database-name"
        ).allowMainThreadQueries().build()
    }
}