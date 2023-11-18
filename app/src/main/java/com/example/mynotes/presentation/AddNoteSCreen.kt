package com.example.mynotes.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mynotes.navigation.NavigationRoutes


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNoteScreen(state: AddNoteScreenstate,
                  eventAddNoteScreenEvent: (AddNoteScreenEvent)->Unit,
                  navigationController: NavController)
{
        // we are passing the state and events of addnotesscreen to addNoteScreen
    Column(verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally, modifier= Modifier.fillMaxSize()){

        OutlinedTextField(value = state.addnotescreeenTitle,
            onValueChange = {eventAddNoteScreenEvent(AddNoteScreenEvent.titleChanged(it))}
        , label = { Text(text = "Title", fontSize = 24.sp, fontFamily = FontFamily.Cursive)}, )

        Divider(
            color = Color.Black,
            thickness = 1.dp, // You can adjust the thickness of the line
            modifier = Modifier.padding(vertical = 8.dp) // Optional padding around the divider
        )
        OutlinedTextField(value = state.addNotescreenContent,
            onValueChange = {eventAddNoteScreenEvent(AddNoteScreenEvent.noteContentChanged(it))}
        ,label = { Text(text = "Title", fontSize = 19.sp, fontFamily = FontFamily.Cursive)})

        Spacer(modifier =  Modifier.height(20.dp))
        Button(onClick = {
            eventAddNoteScreenEvent(AddNoteScreenEvent.save)
            navigationController.navigate(NavigationRoutes.NotesScreen.routes)
        }) {
            Text(text = "Save")
        }
    }
}



