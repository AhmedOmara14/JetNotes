package com.omaradev.jetnote.domain.repository

import com.omaradev.jetnote.domain.model.all_notes.Note
import kotlinx.coroutines.flow.Flow


interface NoteRepository {
    fun getAllNotes(): Flow<List<Note>>

    suspend fun updateNote(note: Note)

    suspend fun deleteNote(note: Note)

    suspend fun getNoteNoteById(id: Int): Note

    suspend fun addNote(note: Note)
}