package com.omaradev.jetnote.ui.save_note.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle

import androidx.compose.ui.unit.dp
import com.omaradev.jetnote.domain.color.ColorModel

@Composable
fun ColorItem(colorModel: ColorModel, onSelectColor: (ColorModel) -> Unit) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier =
        Modifier
            .padding(start = 8.dp, top = 8.dp, end = 8.dp)
            .clickable { onSelectColor(colorModel) }
    ) {
        colorModel.colorId?.let { color->
            Box(
                modifier = Modifier
                    .background(
                        color = colorResource(id = color), shape = CircleShape
                    )
                    .size(50.dp)
                    .border(width = 1.dp, color = Color.Black, shape = CircleShape)
            )
        }
        colorModel.name?.let {
            Text(
                text = it,
                modifier = Modifier.padding(start = 8.dp),
                style = TextStyle()
            )
        }
    }
}