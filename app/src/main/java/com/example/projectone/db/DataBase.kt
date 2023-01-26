package com.example.projectone.db

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataBase {

    @Provides
    fun provideDB(@ApplicationContext context: Context): AppDataBase {
        return Room.databaseBuilder(
            context,
            AppDataBase::class.java, "database-name"
        ).build()
    }

    @Provides
    fun provideNoteDao(appDataBase: AppDataBase): NoteDao {
        return appDataBase.noteDao()
    }

    @Provides
    fun provideUserDao(appDataBase: AppDataBase): UserDao {
        return appDataBase.userDao()
    }
}