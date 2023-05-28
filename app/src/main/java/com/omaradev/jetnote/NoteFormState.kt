package com.omaradev.jetnote

/**
 * Data validation state of the  form.
 */
class NoteFormState(
    val titleNoteError: Int? = null,
    val contentNoteError: Int? = null,
    val colorNoteError: Int? = null
)