package com.example.mynotes.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mynotes.presentation.AddNoteScreen
import com.example.mynotes.presentation.AddNoteScreenEvent
import com.example.mynotes.presentation.NoteEvent
import com.example.mynotes.presentation.NoteScreen
import com.example.mynotes.presentation.NoteState


@Composable
fun AppNavigator(state: NoteState,event:(NoteEvent)->Unit, eventAddNoteScreenEvent:(AddNoteScreenEvent)->Unit)
{
    val navigationcontroller = rememberNavController()





    NavHost(navController = navigationcontroller, startDestination =NavigationRoutes.NotesScreen.routes)
    {

        composable(NavigationRoutes.NotesScreen.routes)
        {
            NoteScreen(state =state , event =event ,navigationcontroller)
        }
        composable(NavigationRoutes.AddNoteScreen.routes)
        {
           AddNoteScreen(state = state, eventAddNoteScreenEvent =eventAddNoteScreenEvent, navigationcontroller )
        }

    }
}