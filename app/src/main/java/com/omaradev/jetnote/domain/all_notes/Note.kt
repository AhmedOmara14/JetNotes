package com.omaradev.jetnote.domain.all_notes

import com.omaradev.jetnote.domain.color.ColorModel

data class Note(
    val id: Int = 0,
    val color: ColorModel,
    val noteTitle: String,
    val noteBody: String,
    val isChecked: Boolean = false
)