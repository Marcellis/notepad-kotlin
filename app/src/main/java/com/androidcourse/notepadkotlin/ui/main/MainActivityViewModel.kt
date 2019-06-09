package com.androidcourse.notepadkotlin.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.androidcourse.notepadkotlin.database.NoteRepository

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {

    private val noteRepository = NoteRepository(application.applicationContext)

    val note = noteRepository.getNotepad()

}