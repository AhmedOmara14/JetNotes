package com.omaradev.jetnote.domain.model.all_notes

import androidx.room.*
import com.omaradev.jetnote.domain.model.color.ColorModel

@Entity(tableName = "Notes")
data class Note(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int ?= 0,

    var color: ColorModel?,

    @ColumnInfo(name = "noteTitle")
    var noteTitle: String?,

    @ColumnInfo(name = "noteBody")
    var noteBody: String?,

    @ColumnInfo(name = "isChecked")
    var isChecked: Boolean = false
)