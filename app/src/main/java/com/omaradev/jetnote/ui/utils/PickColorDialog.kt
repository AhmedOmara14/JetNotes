package com.omaradev.jetnote.ui.utils

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.omaradev.jetnote.domain.model.color.ColorModel
import com.omaradev.jetnote.ui.save_note.component.ColorItem
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PickColorDialog(
    colorsList: List<ColorModel>,
    modalSheetState: ModalBottomSheetState,
    onSelectColor: (ColorModel) -> Unit
) {
    val coroutineScope = rememberCoroutineScope()

    BackHandler(modalSheetState.isVisible) {
        coroutineScope.launch { modalSheetState.hide() }
    }
    ModalBottomSheetLayout(
        sheetState = modalSheetState,
        sheetShape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp),
        sheetContent = {
            LazyColumn {
                items(items = colorsList) { colorModel ->
                    ColorItem(colorModel = colorModel) { selectedColorModel ->
                        onSelectColor(selectedColorModel)
                    }
                }
            }
        },
        sheetBackgroundColor = Color.White,
        modifier = Modifier.padding(bottom = 8.dp)
    ) {}
}