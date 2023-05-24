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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.MutableLiveData
import com.omaradev.jetnote.MainViewModel
import com.omaradev.jetnote.R
import com.omaradev.jetnote.domain.color.ColorModel
import com.omaradev.jetnote.ui.NoteFormState
import com.omaradev.jetnote.ui.save_note.component.ColorItem
import com.omaradev.jetnote.ui.utils.ErrorNote
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@SuppressLint(
    "UnusedMaterialScaffoldPaddingParameter", "CoroutineCreationDuringComposition",
    "UnrememberedMutableState"
)
@Composable
fun SaveNoteScreen(onClickOnBackIcon: () -> Unit, viewModel: MainViewModel) {
    val titleState = rememberSaveable { mutableStateOf("") }
    val contentState = rememberSaveable { mutableStateOf("") }
    val switchState = rememberSaveable { mutableStateOf(true) }

    val noteFormState = MutableLiveData<NoteFormState>()

    val colorModelState = mutableStateOf(ColorModel())

    val coroutineScope = rememberCoroutineScope()
    val pickColorDialogState =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden,
            confirmStateChange = { it != ModalBottomSheetValue.HalfExpanded })

    val colorsList: ArrayList<ColorModel> = arrayListOf(
        ColorModel(0, "Green", R.color.colorPrimary),
        ColorModel(1, "Blue", androidx.compose.ui.R.color.vector_tint_color),
        ColorModel(2, "Black", R.color.black),
        ColorModel(3, "Gray", R.color.gray),
        ColorModel(4, "Red", R.color.red),
        ColorModel(5, "Orange", R.color.orange),
    )


    Scaffold(
        backgroundColor = colorResource(id = R.color.background_color)
    ) {
        Column {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { onClickOnBackIcon() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack, contentDescription = "Arrow Back"
                        )
                    }
                },
                title = {
                    Text(text = "Save Note")
                },
                backgroundColor = colorResource(id = R.color.colorPrimary),
                contentColor = Color.White
            )

            InputText("Title Of Note", titleState)
            ErrorNote(stringResource(id = R.string.required_field))

            InputText("Content Of Note", contentState)
            noteFormState.value?.contentNoteError?.let { ErrorNote(stringResource(id = it)) }

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
            noteFormState.value?.colorNoteError?.let { ErrorNote(stringResource(id = it)) }


            Spacer(modifier = Modifier.padding(8.dp))

            Button(
                onClick = {
                    viewModel.validateSavingNote(
                        titleState.value,
                        contentState.value,
                        colorModelState.value.colorId
                    ).observeForever {
                        noteFormState.postValue(it)
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

        PickColorDialog(colorsList, pickColorDialogState) {
            coroutineScope.launch { pickColorDialogState.hide() }
            colorModelState.value = it
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

@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PickColorDialog(
    colorsList: ArrayList<ColorModel>,
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
            LazyColumn() {
                items(items = colorsList) {
                    ColorItem(colorModel = it) { colorModel ->
                        onSelectColor(colorModel)
                    }
                }
            }
        },
        sheetBackgroundColor = Color.White,
        modifier = Modifier.padding(bottom = 8.dp)
    ) {}
}