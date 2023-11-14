package com.example.mynotes.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mynotes.Data.NotesDaoImpl
import com.example.mynotes.Data.NotesEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class AddNoteScreenViewModel @Inject constructor(
    private val dao:NotesDaoImpl
):ViewModel() {

    private val _state = MutableStateFlow(NoteState())
    val state = _state.asStateFlow()


    fun eventAddScreen(e:AddNoteScreenEvent)
    {

        when(e)
        {
            is AddNoteScreenEvent.noteContentChanged ->
            {
                _state.update {
                    it.copy(
                        notesContentS = e.v
                    )
                }
            }
            AddNoteScreenEvent.save ->{




                viewModelScope.launch(Dispatchers.IO)
                {
                    if(state.value.titleS.isBlank())
                    {
                        return@launch
                    }


                    if(state.value.notesId == null){

                        dao.insertNotes(notesEntity = NotesEntity(
                            notesContent = state.value.notesContentS,
                            title = state.value.titleS
                        ))


                        _state.update {
                            it.copy(
                                titleS = "",
                                notesContentS = ""
                            )
                        }


                    }else{
                        TODO("Update the todo with the id")
                    }

                }

            }
            is AddNoteScreenEvent.titleChanged ->
            {
                _state.update {
                    it.copy(
                        titleS = e.v
                    )
                }
            }
        }

    }
}