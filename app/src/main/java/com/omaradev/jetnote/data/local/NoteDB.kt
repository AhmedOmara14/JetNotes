package com.omaradev.jetnote.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.omaradev.jetnote.domain.model.all_notes.Note
import com.omaradev.jetnote.domain.model.color.ColorModelConverter

@Database(
    entities = [Note::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(ColorModelConverter::class)
abstract class NoteDB : RoomDatabase() {
    abstract val notesDao: NotesDao
}