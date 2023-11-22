package com.example.mynotes.presentation.mainscreenUI

import com.example.mynotes.Data.NotesEntity

data class NoteState(

    val titleS:String = "",
    val notesContentS:String="",
    val notesId:Int?= null,
    val listOfNotes:List<NotesEntity> = emptyList()
    ,val filteredNotes:List<NotesEntity> = emptyList()
    , val searchQuery:String = ""
)
