package com.omaradev.jetnote.ui.all_notes

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import com.omaradev.jetnote.MainViewModel
import com.omaradev.jetnote.R
import com.omaradev.jetnote.domain.model.all_notes.Note
import com.omaradev.jetnote.ui.all_notes.component.NoteItem

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AllNotesScreen(
    onOpenNavDrawer: () -> Unit,
    onClickSaveNote: () -> Unit,
    onClickNoteItem: (Note) -> Unit,
    viewModel: MainViewModel
) {
    val notes = viewModel.getAllNotes().collectAsState(initial = emptyList())

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

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(items = notes.value) { it ->
                    NoteItem(note = it, onClickNote = {
                        onClickNoteItem(it)
                    }, onChangeCheckedNote = {})
                }
            }

        }
    }
}