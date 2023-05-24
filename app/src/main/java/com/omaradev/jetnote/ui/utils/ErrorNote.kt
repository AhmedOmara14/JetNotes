package com.omaradev.jetnote.ui.utils
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.omaradev.jetnote.R

@Composable
fun ErrorNote(requiredAction:String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .size(25.dp)
            .padding(start = 8.dp, top = 1.dp, end = 8.dp)
            .background(
                color = colorResource(id = R.color.red_),
                shape = RoundedCornerShape(10.dp)
            ),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Image(
            imageVector = Icons.Filled.Info,
            contentDescription = null,
            modifier = Modifier.padding(2.dp),
            colorFilter = ColorFilter.tint(color = Color.Red)
        )

        Text(
            text = requiredAction,
            style = TextStyle(fontSize = 14.sp),
            color = Color.DarkGray,
            modifier = Modifier.padding(start = 4.dp)
        )
    }
}