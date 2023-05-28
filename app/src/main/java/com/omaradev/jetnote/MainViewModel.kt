package com.omaradev.jetnote

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainViewModel : ViewModel() {
    private val _formState = MutableStateFlow(NoteFormState())
    val formState: StateFlow<NoteFormState> = _formState

    fun validateSavingNote(
        title: String?, content: String?, colorId: Int?
    ) {
        _formState.value =
            if (title.isNullOrBlank()) NoteFormState(titleNoteError = R.string.required_field)
            else (if (content.isNullOrBlank()) NoteFormState(contentNoteError = R.string.required_field)
            else if (colorId == null) NoteFormState(colorNoteError = R.string.required_field) else null) as NoteFormState
    }
}