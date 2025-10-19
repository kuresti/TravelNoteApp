package com.example.travelnoteapp.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class TravelNoteViewModelFactory(private val noteDao: NoteDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TravelNoteViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TravelNoteViewModel(noteDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}