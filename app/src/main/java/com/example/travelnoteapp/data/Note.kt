package com.example.travelnoteapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

/********************************
 * Note Class
 * defines what is included in a note
 ********************************/
@Entity(tableName = "notes")
data class Note(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val content: String,
    val updatedAt: Long = System.currentTimeMillis()
)