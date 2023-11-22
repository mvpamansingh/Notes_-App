package com.example.mynotes.presentation.AddScreenUi

sealed interface AddNoteScreenEvent
{
    data class titleChanged(val v:String): AddNoteScreenEvent
    data class noteContentChanged(val v :String): AddNoteScreenEvent

    object save: AddNoteScreenEvent
}