package com.example.mynotes.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mynotes.presentation.AddScreenUi.AddNoteScreen
import com.example.mynotes.presentation.AddScreenUi.AddNoteScreenViewModel
import com.example.mynotes.presentation.mainscreenUI.NoteScreen
import com.example.mynotes.presentation.mainscreenUI.NotesViewModel


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
            NoteScreen(state =state , event =vm::event,vm ,navigationcontroller)
        }
        composable(NavigationRoutes.AddNoteScreen.routes)
        {
            val addscreenVM = hiltViewModel<AddNoteScreenViewModel>()
            val addstate by addscreenVM.addnotescreenstate.collectAsState()
            
           AddNoteScreen(state = addstate, eventAddNoteScreenEvent =addscreenVM::eventAddScreen, navigationcontroller )
        }

    }
}