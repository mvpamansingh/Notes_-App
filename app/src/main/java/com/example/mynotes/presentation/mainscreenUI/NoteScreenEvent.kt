package com.example.mynotes.presentation.mainscreenUI

import com.example.mynotes.Data.NotesEntity

sealed interface NoteEvent
{
//    data class titleChanged(val v:String): NoteEvent
//    data class noteContentChanged(val v :String): NoteEvent
//
//    object save: NoteEvent

    object clearAll: NoteEvent






    data class deleteContact(val v : NotesEntity): NoteEvent
    data class onSearchQueryChanged(val v: String):NoteEvent
}