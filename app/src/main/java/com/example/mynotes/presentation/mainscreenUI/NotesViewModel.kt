package com.example.mynotes.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mynotes.Data.NotesDaoImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class NotesViewModel @Inject constructor(
    private val dao:NotesDaoImpl
):ViewModel() {


    private val _state = MutableStateFlow(NoteState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO)
        {
            delay(300)
            dao.getAllNotes().collect{list->
                _state.update {
                    it.copy(listOfNotes = list)
                }
            }
        }
    }

    fun event(e:NoteEvent)
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


        }
    }

}