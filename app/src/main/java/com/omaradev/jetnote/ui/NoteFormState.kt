package com.omaradev.jetnote.ui

/**
 * Data validation state of the  form.
 */
data class NoteFormState(
    var titleNoteError: Int? = null,
    val contentNoteError: Int? = null,
    val colorNoteError: Int? = null
)