package com.omaradev.jetnote
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.omaradev.jetnote.ui.NoteFormState

class MainViewModel : ViewModel() {
    val noteForm = MutableLiveData<NoteFormState>()

    fun validateSavingNote(
        title: String?, content: String?, colorId: Int?
    ): MutableLiveData<NoteFormState> {

        noteForm.value =
            if (title.isNullOrBlank()) NoteFormState(titleNoteError = R.string.required_field)
            else if (content.isNullOrBlank()) NoteFormState(contentNoteError = R.string.required_field)
            else if (colorId == null) NoteFormState(colorNoteError = R.string.required_field)
            else null

        return noteForm
    }
}