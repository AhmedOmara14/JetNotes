package com.omaradev.jetnote.ui.all_notes.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.omaradev.jetnote.domain.model.all_notes.Note
import com.omaradev.jetnote.ui.all_notes.SwipeToDeleteItem

@Composable
fun NoteItem(note: Note, onClickNote: (Note) -> Unit, onDeleteNote: (Note) -> Unit) {

    SwipeToDeleteItem(content = {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, top = 7.dp, end = 8.dp, bottom = 1.dp)
                .shadow(1.dp, RoundedCornerShape(4.dp))
                .background(Color.White, RoundedCornerShape(4.dp))
            ,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,

            ) {
            val checkedState = rememberSaveable { mutableStateOf(note.isChecked) }

            note.color?.colorId?.let {
                Box(modifier = Modifier
                    .padding(8.dp)
                    .background(color = colorResource(id = it), shape = CircleShape)
                    .size(60.dp)
                    .clickable { onClickNote(note) }
                    .border(width = 1.dp, color = Color.Black, shape = CircleShape))
            }


            Spacer(modifier = Modifier.padding(10.dp))

            Column(Modifier.weight(1f)) {
                note.noteTitle?.let {
                    Text(
                        text = it, style = TextStyle(
                            color = Color.Black, fontSize = 18.sp, fontWeight = FontWeight.SemiBold
                        ), maxLines = 1
                    )
                }
                note.noteBody?.let {
                    Text(
                        text = it, style = TextStyle(
                            color = Color.Gray, fontSize = 15.sp, fontWeight = FontWeight.Light
                        ), maxLines = 2
                    )
                }
            }

            Spacer(modifier = Modifier.padding(10.dp))

            Checkbox(
                checked = checkedState.value, onCheckedChange = {
                    checkedState.value = it
                }, colors = CheckboxDefaults.colors(
                    checkedColor = Color.Black.copy(.15f),
                    uncheckedColor = Color.Gray,
                    checkmarkColor = Color.Black
                )
            )
        }
    }) {
        onDeleteNote(note)
    }

}