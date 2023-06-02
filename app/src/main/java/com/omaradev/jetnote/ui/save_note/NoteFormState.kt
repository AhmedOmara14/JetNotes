package com.omaradev.jetnote.ui.save_note

/**
 * Data validation state of the  form.
 */
class NoteFormState(
    val titleNoteError: Int? = null,
    val contentNoteError: Int? = null,
    val colorNoteError: Int? = null
)