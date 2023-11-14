package com.example.mynotes.navigation



sealed class NavigationRoutes( val routes:String)
{
    object NotesScreen:NavigationRoutes("notes_screen")
    object AddNoteScreen:NavigationRoutes("addNote_screen")

}

