package com.example.projectone

import android.app.Application
import com.example.projectone.db.DataBase

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        DataBase.initDB(applicationContext)
    }
}