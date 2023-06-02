package com.omaradev.jetnote.data.local

import androidx.room.*
import androidx.room.OnConflictStrategy.Companion.IGNORE
import com.omaradev.jetnote.domain.model.all_notes.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NotesDao {
    @Insert(onConflict = IGNORE)
    suspend fun addNote(note: Note)

    @Query("SELECT * FROM Notes ORDER BY id ASC")
    fun getAllNotes(): Flow<List<Note>>

    @Query("SELECT * FROM Notes WHERE id =:id")
    suspend fun getNote(id: Int): Note

    @Delete
    suspend fun deleteNote(note: Note)

    @Update
    suspend fun updateNote(note: Note)
}