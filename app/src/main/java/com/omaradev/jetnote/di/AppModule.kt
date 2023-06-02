package com.omaradev.jetnote.di

import android.app.Application
import androidx.room.Room
import com.omaradev.jetnote.data.local.NoteDB
import com.omaradev.jetnote.domain.repository.NoteRepository
import com.omaradev.jetnote.data.repository.NoteRepositoryImpl
import com.omaradev.jetnote.data.local.NotesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideNoteDB(application: Application): NoteDB {
        return Room.databaseBuilder(application, NoteDB::class.java, "note_database")
            .build()
    }

    @Provides
    @Singleton
    fun provideNoteDao(noteDB: NoteDB) = noteDB.notesDao

    @Provides
    @Singleton
    fun provideNoteRepository(notesDao: NotesDao): NoteRepository = NoteRepositoryImpl(notesDao)
}