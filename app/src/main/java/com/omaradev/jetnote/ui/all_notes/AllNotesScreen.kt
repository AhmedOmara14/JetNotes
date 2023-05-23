package com.omaradev.jetnote.ui.all_notes

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import com.omaradev.jetnote.R
import com.omaradev.jetnote.domain.all_notes.Note
import com.omaradev.jetnote.domain.color.ColorModel
import com.omaradev.jetnote.ui.all_notes.component.NoteItem

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AllNotesScreen(onOpenNavDrawer: () -> Unit, onClickSaveNote: () -> Unit) {
    val context = LocalContext.current

    Scaffold(
        backgroundColor = colorResource(id = R.color.background_color),
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onClickSaveNote() },
                contentColor = Color.White,
                backgroundColor = colorResource(id = R.color.colorPrimary)
            ) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "Floating Action Button")
            }
        },
    ) {
        Column {
            TopAppBar(backgroundColor = colorResource(id = R.color.colorPrimary),
                contentColor = Color.White,
                navigationIcon = {
                    IconButton(onClick = { onOpenNavDrawer() }) {
                        Icon(Icons.Filled.Menu, null)
                    }
                },
                title = {
                    Text(text = stringResource(id = R.string.app_name))
                })

            val list = arrayListOf(
                Note(
                    color = ColorModel(1, "", R.color.colorPrimary),
                    noteTitle = "test title",
                    noteBody = "test body",
                    isChecked = false
                ), Note(
                    color = ColorModel(1, "", R.color.colorPrimary),
                    noteTitle = "test title",
                    noteBody = "test body",
                    isChecked = false
                ), Note(
                    color = ColorModel(1, "", R.color.colorPrimary),
                    noteTitle = "test title",
                    noteBody = "test body",
                    isChecked = false
                ), Note(
                    color = ColorModel(1, "", R.color.colorPrimary),
                    noteTitle = "test title",
                    noteBody = "test body",
                    isChecked = true
                ), Note(
                    color = ColorModel(1, "", R.color.colorPrimary),
                    noteTitle = "test title",
                    noteBody = "test body",
                    isChecked = true
                )
            )
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(items = list) {
                    NoteItem(note = it, onClickNote = {
                        Toast.makeText(context, it.noteTitle, Toast.LENGTH_SHORT).show()
                    }, onChangeCheckedNote = {
                        Toast.makeText(context, it.noteTitle, Toast.LENGTH_SHORT).show()
                    })
                }
            }

        }
    }
}