package com.example.mynotes.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mynotes.presentation.AddNoteScreen
import com.example.mynotes.presentation.AddNoteScreenEvent
import com.example.mynotes.presentation.AddNoteScreenViewModel
import com.example.mynotes.presentation.NoteEvent
import com.example.mynotes.presentation.NoteScreen
import com.example.mynotes.presentation.NoteState
import com.example.mynotes.presentation.NotesViewModel


@Composable
fun AppNavigator()
{
    val navigationcontroller = rememberNavController()





    NavHost(navController = navigationcontroller, startDestination =NavigationRoutes.NotesScreen.routes)
    {

        composable(NavigationRoutes.NotesScreen.routes)
        {
            val vm= hiltViewModel<NotesViewModel>()

            val state by vm.state.collectAsState()
            NoteScreen(state =state , event =vm::event ,navigationcontroller)
        }
        composable(NavigationRoutes.AddNoteScreen.routes)
        {
            val addscreenVM = hiltViewModel<AddNoteScreenViewModel>()
            val state by addscreenVM.state.collectAsState()
           AddNoteScreen(state = state, eventAddNoteScreenEvent =addscreenVM::eventAddScreen, navigationcontroller )
        }

    }
}