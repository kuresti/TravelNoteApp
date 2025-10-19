package com.example.travelnoteapp.data

import androidx.room.Database
import androidx.room.RoomDatabase


/********************************
 * The Database class is the main
 * access point for the database
 ********************************/
@Database(entities = [Note::class], version = 3, exportSchema = false)
abstract class NoteDB : RoomDatabase() {
    abstract fun noteDao() : NoteDao
}



