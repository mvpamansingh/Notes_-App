package com.example.mynotes.presentation.mainscreenUI

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mynotes.Data.NotesDaoImpl
import com.example.mynotes.Data.NotesEntity
import com.example.mynotes.presentation.mainscreenUI.NoteEvent
import com.example.mynotes.presentation.mainscreenUI.NoteState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class NotesViewModel @Inject constructor(
    private val dao:NotesDaoImpl
):ViewModel() {


    private val _state = MutableStateFlow(NoteState())
    val state = _state.asStateFlow()

    // This state will hold the search query
    private val _searchQuery = MutableStateFlow("")

    // This will hold the list of filtered notes based on the search query
    private val _filteredNotes = MutableStateFlow(emptyList<NotesEntity>())

    // Public view of the filtered notes
    val filteredNotes = _filteredNotes.asStateFlow()

//    init {                without search
//        viewModelScope.launch(Dispatchers.IO)
//        {
//            delay(300)
//            dao.getAllNotes().collect{list->
//                _state.update {
//                    it.copy(listOfNotes = list)
//                }
//            }
//        }
//    }
//    init {                my wrng implementation
//        // Whenever the search query changes, the notes list is updated
//        viewModelScope.launch(Dispatchers.IO) {
//           _state.value.searchQuery.flatMapLatest { query ->
//                if (query.isEmpty()) {
//                    dao.getAllNotes() // If the search query is empty, show all notes
//                } else {
//                    dao.getAllNotes().map { list ->
//                        list.filter {
//                            it.title.contains(query, ignoreCase = true) || it.notesContent.contains(query, ignoreCase = true)
//                        }
//                    }
//                }
//            }.collect { filteredList ->
//                _state.value.filteredNotes = filteredList
//            }
//        }
//    }  mine
init {
    viewModelScope.launch(Dispatchers.IO) {
        delay(300)
        dao.getAllNotes().collect { list ->
            _state.update { currentState ->
                // If searchQuery is not empty, filter the list based on the search query
                if (currentState.searchQuery.isNotEmpty()) {
                    val filteredList = list.filter {
                        it.title.contains(currentState.searchQuery, ignoreCase = true) ||
                                it.notesContent.contains(currentState.searchQuery, ignoreCase = true)
                    }
                    currentState.copy(listOfNotes = list, filteredNotes = filteredList)
                } else {
                    currentState.copy(listOfNotes = list, filteredNotes = list)
                }
            }
        }
    }
}
    fun event(e: NoteEvent)
    {
        when(e)
        {
            NoteEvent.clearAll ->
            {

                viewModelScope.launch(Dispatchers.IO) {
                    dao.clearTable()
                }
            }
            is NoteEvent.deleteContact ->
            {
                viewModelScope.launch(Dispatchers.IO) {
                    dao.deleteNotes(e.v)
                }
            }

            is NoteEvent.onSearchQueryChanged ->
            {
                _state.update {
                    it.copy(
                        searchQuery = e.v
                    )
                }
                val query= _state.value.searchQuery
                _state.update { currentState ->
                    // Update the searchQuery and filter listOfNotes based on the new query
                    val filteredList = if (_state.value.searchQuery.isEmpty()) {
                        currentState.listOfNotes
                    } else {
                        currentState.listOfNotes.filter {
                            it.title.contains(_state.value.searchQuery, ignoreCase = true) ||
                                    it.notesContent.contains(_state.value.searchQuery, ignoreCase = true)
                        }
                    }
                    currentState.copy(searchQuery = _state.value.searchQuery, filteredNotes = filteredList)
                }
            }
        }
    }

}