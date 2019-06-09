package com.androidcourse.notepadkotlin.ui.edit

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.androidcourse.notepadkotlin.database.NoteRepository
import com.androidcourse.notepadkotlin.model.Note
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditViewModel(application: Application) : AndroidViewModel(application) {

    private val noteRepository = NoteRepository(application.applicationContext)
    private val ioScope = CoroutineScope(Dispatchers.IO)

    val note = MutableLiveData<Note?>()
    val error = MutableLiveData<String?>()
    val success = MutableLiveData<Boolean>()

    fun updateNote() {
        if (isNoteValid()) {
            ioScope.launch {
                noteRepository.updateNotepad(note.value!!)
                success.value = true
            }
        }
    }

    private fun isNoteValid(): Boolean {
        return when {
            note.value == null -> {
                error.value = "Note must not be null"
                false
            }
            note.value!!.title.isBlank() -> {
                error.value = "Title must not be empty"
                false
            }
            else -> true
        }
    }

}