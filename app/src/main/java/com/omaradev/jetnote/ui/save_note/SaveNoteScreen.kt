package com.omaradev.jetnote.ui.save_note

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.omaradev.jetnote.MainViewModel
import com.omaradev.jetnote.R
import com.omaradev.jetnote.domain.model.color.ColorModel
import com.omaradev.jetnote.ui.save_note.component.ColorItem
import com.omaradev.jetnote.ui.utils.ErrorNote
import com.omaradev.jetnote.ui.utils.InputText
import com.omaradev.jetnote.ui.utils.PickColorDialog
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@SuppressLint(
    "UnusedMaterialScaffoldPaddingParameter",
    "CoroutineCreationDuringComposition",
    "UnrememberedMutableState"
)
@Composable
fun SaveNoteScreen(onClickOnBackIcon: () -> Unit, viewModel: MainViewModel) {
    val titleState = mutableStateOf("")
    val contentState = mutableStateOf("")
    val switchState = mutableStateOf(true)
    val colorModelState = mutableStateOf(ColorModel(0, ""))
    val formState = mutableStateOf<NoteFormState?>(NoteFormState())
    val coroutineScope = rememberCoroutineScope()
    val pickColorDialogState =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden,
            confirmValueChange = { it != ModalBottomSheetValue.HalfExpanded })

    val colorsList = remember {
        mutableStateListOf(
            ColorModel(0, "Green", R.color.colorPrimary),
            ColorModel(1, "Blue", R.color.blue),
            ColorModel(2, "Black", R.color.black),
            ColorModel(3, "Gray", R.color.gray),
            ColorModel(4, "Red", R.color.red),
            ColorModel(5, "Orange", R.color.orange)
        )
    }


    Scaffold(
        backgroundColor = colorResource(id = R.color.background_color)
    ) {
        Column {
            TopAppBar(navigationIcon = {
                IconButton(onClick = onClickOnBackIcon) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack, contentDescription = "Arrow Back"
                    )
                }
            },
                title = { Text(text = "Save Note") },
                backgroundColor = colorResource(id = R.color.colorPrimary),
                contentColor = Color.White
            )

            InputText("Title Of Note", titleState)
            formState.value?.titleNoteError?.let {
                ErrorNote(stringResource(id = it))
            }

            InputText("Content Of Note", contentState)
            formState.value?.contentNoteError?.let {
                ErrorNote(stringResource(id = it))
            }

            Spacer(modifier = Modifier.padding(8.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .layoutId("SaveNote")
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
                        uncheckedThumbColor = colorResource(id = R.color.black).copy(alpha = .5f),
                        uncheckedTrackColor = colorResource(id = R.color.black).copy(alpha = .8f)
                    )
                )
            }

            Spacer(modifier = Modifier.padding(8.dp))

            Box(modifier = Modifier.fillMaxWidth()) {
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
                    .background(
                        color = colorResource(
                            id = colorModelState.value.colorId ?: R.color.white
                        ), shape = CircleShape
                    )
                    .align(Alignment.CenterEnd)
                    .size(40.dp)
                    .clickable {
                        coroutineScope.launch {
                            pickColorDialogState.show()
                        }
                    }
                    .border(width = 1.dp, color = Color.Black, shape = CircleShape))
            }
            formState.value?.colorNoteError?.let {
                ErrorNote(stringResource(id = it))
            }

            Spacer(modifier = Modifier.padding(8.dp))

            Button(
                onClick = {
                    coroutineScope.launch {
                        viewModel.validateSavingNote(
                            titleState.value,
                            contentState.value,
                            colorModelState.value.colorId,
                            switchState.value
                        ).collect {
                            it?.let { formState.value = it } ?: kotlin.run {
                                titleState.value = ""
                                contentState.value = ""
                                switchState.value = false
                                colorModelState.value = ColorModel(0, "")
                                formState.value = null
                            }
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, end = 8.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = colorResource(id = R.color.colorPrimary),
                    contentColor = Color.White
                )
            ) {
                Text(text = "Save")
            }
        }

        PickColorDialog(colorsList, pickColorDialogState) { selectedColorModel ->
            coroutineScope.launch { pickColorDialogState.hide() }
            colorModelState.value = selectedColorModel
        }
    }
}



