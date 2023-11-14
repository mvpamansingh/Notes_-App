package com.example.mynotes.Data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NotesEntity(

    @PrimaryKey(autoGenerate = true)
    val id:Int?= null,
    val title:String,
    val notesContent:String
)
