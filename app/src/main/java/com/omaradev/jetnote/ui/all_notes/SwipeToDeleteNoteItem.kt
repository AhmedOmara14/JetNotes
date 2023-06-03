package com.omaradev.jetnote.ui.all_notes

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp

@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SwipeToDeleteItem(
    content: @Composable () -> Unit, onDismiss: () -> Unit
) {
    val dismissState = rememberDismissState(confirmStateChange = {
        false
    })

    //Delete After to Swipe to Center Of Screen
    if (dismissState.offset.value >=800) onDismiss()

    SwipeToDismiss(
        state = dismissState,
        background = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 8.dp, top = 7.dp, end = 8.dp, bottom = 1.dp)
                    .background(Color.Red, RoundedCornerShape(4.dp)),
                contentAlignment = Alignment.CenterStart
            ) {
                Image(
                    imageVector = Icons.Filled.Delete,
                    contentDescription = "",
                    colorFilter = ColorFilter.tint(color = Color.White),
                    modifier = Modifier.padding(8.dp)
                )
            }
        },
        directions = setOf(DismissDirection.StartToEnd)
    ) {
        content()
    }
}

