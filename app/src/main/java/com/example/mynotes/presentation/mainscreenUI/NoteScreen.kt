package com.example.mynotes.presentation.mainscreenUI

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mynotes.Data.NotesEntity
import com.example.mynotes.navigation.NavigationRoutes


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteScreen(state: NoteState,
               event: (NoteEvent)->Unit,
               viewModel: NotesViewModel,
               navigationController:NavController)
{
    //val state = viewModel.state.collectAsState().value
   // val filteredNotes = viewModel.filteredNotes.collectAsState().value
    //val searchQuery = rememberSaveable { mutableStateOf("") }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {

                // Navigate to Addnotescreen
                navigationController.navigate(NavigationRoutes.AddNoteScreen.routes)
            }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) {paddingValues ->


//linear
//        LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp),
//            contentPadding = PaddingValues(horizontal = 10.dp)
//        ){
//
//           items(state.listOfNotes)
//           {notelist->
//                NoteCard(notelist = notelist, event =event )
//
//           }
//        }


        Column(modifier= Modifier.fillMaxSize()) {
            Spacer(modifier =Modifier.height(19.dp))

            Text(text = "Notes", fontSize = 32.sp, modifier = Modifier.padding(start = 10.dp))
            
            Spacer(modifier = Modifier.height(25.dp))



            SearchBar(
                state = state,
//                onQueryChanged = {
////                    searchQuery.value = it
////                    viewModel.onSearchQueryChanged(it)
//                    event(NoteEvent.onSearchQueryChanged(it))
//                },
                event= event

            )

//            LazyVerticalStaggeredGrid(
//                columns = StaggeredGridCells.Fixed(2),
//                verticalItemSpacing = 4.dp,
//                horizontalArrangement = Arrangement.spacedBy(4.dp),
//                content = {
//
//                    items(state.listOfNotes)
//                    {notelist->
//                        NoteCard(notelist = notelist, event = event)
//                    }
//                },
//                modifier = Modifier.fillMaxSize()
//            )

            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Fixed(2),
                verticalItemSpacing = 4.dp,
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                content = {

                    items(state.filteredNotes) { notelist ->
                        NoteCard(notelist = notelist, event = { event -> viewModel.event(event) })
                    }
                },
                modifier = Modifier.fillMaxSize()
            )
        }


    }
}


@Composable
fun NoteCard(notelist:NotesEntity, event:(NoteEvent)->Unit)
{

    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            ,
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize() // Ensures the Row fills the card completely
                .padding(end = 8.dp) // Adjust or remove padding as needed
        ) {
            // Texts column
            Column(
                modifier = Modifier
                    .weight(1f) // Adjust this weight to control space distribution
                    .padding(16.dp), // Padding for internal spacing
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.Start
            ) {
                Text(text = notelist.title, fontSize =24.sp, modifier = Modifier.fillMaxWidth())
                Text(text = notelist.notesContent, fontSize = 20.sp, modifier= Modifier.fillMaxWidth())

            }

            // Delete button
            IconButton(
                onClick = {
                       //   event(NoteEvent.deleteContact(notelist))
                          event(NoteEvent.deleteContact(notelist))
                          },
                modifier = Modifier
                    .align(Alignment.CenterVertically) // Align the button vertically
            ) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete")
            }
        }
    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(state: NoteState,  event:(NoteEvent)->Unit) {
    OutlinedTextField(
        value = state.searchQuery,
        onValueChange = { event(NoteEvent.onSearchQueryChanged(it)) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        placeholder = { Text("Search here...") },
        singleLine = true,
        leadingIcon = { Icon(Icons.Filled.Search, contentDescription = "Search Icon") }
    )
}
