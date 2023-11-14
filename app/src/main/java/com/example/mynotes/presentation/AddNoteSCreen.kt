package com.example.mynotes.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNoteScreen(state: NoteState,
                  eventAddNoteScreenEvent: (AddNoteScreenEvent)->Unit)
{

    Column(verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally){

        OutlinedTextField(value = state.titleS, onValueChange = {eventAddNoteScreenEvent(AddNoteScreenEvent.titleChanged(it))})
        OutlinedTextField(value = state.notesContentS, onValueChange = {eventAddNoteScreenEvent(AddNoteScreenEvent.noteContentChanged(it))})

        Button(onClick = { eventAddNoteScreenEvent(AddNoteScreenEvent.save) }) {
            Text(text = "Save")
        }
    }
}