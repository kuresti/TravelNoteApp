package com.example.travelnoteapp.data

import android.content.Context
import androidx.room.Room

/********************************
 * Singleton patter to ensure only
 * a single instance of the DB is
 * created.
 ********************************/
object DBProvider {

    @Volatile
    private var INSTANCE: NoteDB? = null

    fun getDatabase(context: Context): NoteDB {
        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                NoteDB::class.java,
                "travelNote_database"
            ).build()
            INSTANCE = instance
            instance
        }
    }
}