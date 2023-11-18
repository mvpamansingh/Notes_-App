package com.example.mynotes.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mynotes.navigation.NavigationRoutes


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNoteScreen(state: AddNoteScreenstate,
                  eventAddNoteScreenEvent: (AddNoteScreenEvent)->Unit,
                  navigationController: NavController)
{

    Column(verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally){

        OutlinedTextField(value = state.addnotescreeenTitle, onValueChange = {eventAddNoteScreenEvent(AddNoteScreenEvent.titleChanged(it))})
        OutlinedTextField(value = state.addNotescreenContent, onValueChange = {eventAddNoteScreenEvent(AddNoteScreenEvent.noteContentChanged(it))})

        Spacer(modifier =  Modifier.height(20.dp))
        Button(onClick = {
            eventAddNoteScreenEvent(AddNoteScreenEvent.save)
            navigationController.navigate(NavigationRoutes.NotesScreen.routes)
        }) {
            Text(text = "Save")
        }
    }
}



