package com.omaradev.jetnote.ui.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun InputText(label: String, inputState: MutableState<String>) {
    TextField(value = inputState.value,
        onValueChange = { inputState.value = it },
        label = { Text(text = label) },
        modifier = Modifier
            .padding(start = 8.dp, end = 8.dp, top = 8.dp)
            .fillMaxWidth()
            .background(
                color = Color.Black.copy(alpha = .1f), shape = RoundedCornerShape(4.dp)
            ),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent,
            cursorColor = Color.Black,
            focusedIndicatorColor = Color.Transparent,
            focusedLabelColor = Color.Black.copy(alpha = .5f),
            textColor = Color.Black,
            unfocusedLabelColor = Color.Black.copy(alpha = .5f)
        )
    )
}
