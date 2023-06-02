package com.omaradev.jetnote

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.omaradev.jetnote.domain.repository.NoteRepository
import com.omaradev.jetnote.domain.model.all_notes.Note
import com.omaradev.jetnote.domain.model.color.ColorModel
import com.omaradev.jetnote.ui.save_note.NoteFormState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: NoteRepository
) : ViewModel() {

    var note by mutableStateOf(Note())

    fun validateSavingNote(
        title: String?, content: String?, colorId: Int?, switchValue: Boolean
    ): MutableStateFlow<NoteFormState?> {
        val formState:MutableStateFlow<NoteFormState?> = MutableStateFlow(NoteFormState())

        if (title.isNullOrBlank()) {
            formState.value = NoteFormState(titleNoteError = R.string.required_field)
        } else if (content.isNullOrBlank()) {
            formState.value = NoteFormState(contentNoteError = R.string.required_field)
        } else if (colorId == null) {
            formState.value = NoteFormState(colorNoteError = R.string.required_field)
        } else {
            addNote(
                Note(
                    noteTitle = title,
                    color = ColorModel(colorId = colorId),
                    noteBody = content,
                    isChecked = switchValue ,
                )
            )
            formState.value = null
        }
        return formState
    }

    private fun addNote(note: Note) = viewModelScope.launch { repository.addNote(note) }
    fun deleteNote(note: Note) = viewModelScope.launch { repository.deleteNote(note) }
    fun updateNote(note: Note) = viewModelScope.launch { repository.updateNote(note) }
    fun getNoteById(id: Int) = viewModelScope.launch {
        note = repository.getNoteNoteById(id)
    }

    fun getAllNotes() = repository.getAllNotes()
}