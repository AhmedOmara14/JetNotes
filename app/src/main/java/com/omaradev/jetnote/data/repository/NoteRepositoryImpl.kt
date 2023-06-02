package com.omaradev.jetnote.data.repository

import com.omaradev.jetnote.domain.repository.NoteRepository
import com.omaradev.jetnote.data.local.NotesDao
import com.omaradev.jetnote.domain.model.all_notes.Note
import kotlinx.coroutines.flow.Flow

class NoteRepositoryImpl(
    private val notesDao: NotesDao
) : NoteRepository {
    override fun getAllNotes(): Flow<List<Note>> = notesDao.getAllNotes()

    override suspend fun updateNote(note: Note) = notesDao.updateNote(note = note)

    override suspend fun deleteNote(note: Note) = notesDao.deleteNote(note = note)

    override suspend fun getNoteNoteById(id: Int) = notesDao.getNote(id)

    override suspend fun addNote(note: Note) = notesDao.addNote(note = note)
}