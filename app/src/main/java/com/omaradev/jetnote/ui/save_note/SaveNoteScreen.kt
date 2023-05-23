package com.omaradev.jetnote.ui.save_note

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.omaradev.jetnote.R

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SaveNoteScreen(onClickOnBackIcon: () -> Unit) {
    val titleState = rememberSaveable { mutableStateOf("") }
    val contentState = rememberSaveable { mutableStateOf("") }
    val switchState = rememberSaveable { mutableStateOf(true) }

    Scaffold(
        backgroundColor = colorResource(id = R.color.background_color)
    ) {
        Column {
            TopAppBar(navigationIcon = {
                IconButton(onClick = { onClickOnBackIcon() }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack, contentDescription = "Arrow Back"
                    )
                }
            }, title = {
                Text(text = "Save Note")
            }, backgroundColor = colorResource(id = R.color.colorPrimary)
            )

            InputText("Title Of Note", titleState)

            InputText("Content Of Note", contentState)

            Spacer(modifier = Modifier.padding(8.dp))

            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Can Note Be CheckOff ??",
                    color = Color.Black,
                    style = TextStyle(fontSize = 15.sp),
                    modifier = Modifier
                        .padding(start = 8.dp, end = 8.dp)
                        .align(Alignment.CenterStart)
                )

                Switch(
                    checked = switchState.value,
                    onCheckedChange = { switchState.value = it },
                    modifier = Modifier.align(Alignment.TopEnd),
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = colorResource(id = R.color.colorPrimary),
                        checkedTrackColor = colorResource(id = R.color.black),

                        uncheckedThumbColor = colorResource(id = R.color.black).copy(.5f),
                        uncheckedTrackColor = colorResource(id = R.color.black).copy(.8f)
                    ),

                    )
            }

            Spacer(modifier = Modifier.padding(8.dp))

            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Picked Color",
                    color = Color.Black,
                    style = TextStyle(fontSize = 15.sp),
                    modifier = Modifier
                        .padding(start = 8.dp, end = 8.dp)
                        .align(Alignment.CenterStart)
                )

                Box(modifier = Modifier
                    .padding(8.dp)
                    .background(color = colorResource(id = R.color.white), shape = CircleShape)
                    .align(Alignment.CenterEnd)
                    .size(40.dp)
                    .clickable {}
                    .border(width = 1.dp, color = Color.Black, shape = CircleShape))
            }

            Spacer(modifier = Modifier.padding(8.dp))

        }
    }
}

@Composable
fun InputText(label: String, inputState: MutableState<String>) {
    TextField(
        value = inputState.value,
        onValueChange = {
            inputState.value = it
        },
        label = { Text(text = label) },
        modifier = Modifier
            .padding(start = 8.dp, end = 8.dp, top = 8.dp)
            .fillMaxWidth()
            .background(
                color = Color.Black.copy(.1f), RoundedCornerShape(4.dp)
            ),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent,
            cursorColor = Color.Black,
            focusedIndicatorColor = Color.Transparent,
            focusedLabelColor = Color.Black.copy(.5f),
            textColor = Color.Black,
            unfocusedLabelColor = Color.Black.copy(.5f)
        ),
    )
}