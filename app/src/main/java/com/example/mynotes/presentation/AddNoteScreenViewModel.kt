package com.example.mynotes.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mynotes.Data.NotesDaoImpl
import com.example.mynotes.Data.NotesEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject






@HiltViewModel
class AddNoteScreenViewModel @Inject constructor(
    private val dao:NotesDaoImpl
):ViewModel() {

    private val _state = MutableStateFlow(NoteState())
    val state = _state.asStateFlow()

    private val _addnotescreenstate= MutableStateFlow(AddNoteScreenstate())
    val addnotescreenstate= _addnotescreenstate.asStateFlow()

    fun eventAddScreen(e:AddNoteScreenEvent)
    {

        when(e)
        {
            is AddNoteScreenEvent.noteContentChanged ->
            {
                _addnotescreenstate.update {
                    it.copy(
                        addNotescreenContent = e.v
                    )
                }
            }
            AddNoteScreenEvent.save ->{


                viewModelScope.launch(Dispatchers.IO)
                {
                    if(addnotescreenstate.value.addnotescreeenTitle.isBlank())
                    {
                        return@launch
                    }


                    if(addnotescreenstate.value.notesId == null){

                        dao.insertNotes(notesEntity = NotesEntity(
                            title= addnotescreenstate.value.addnotescreeenTitle,
                            notesContent = addnotescreenstate.value.addNotescreenContent
                        ))


                        _addnotescreenstate.update {
                            it.copy(
                                addnotescreeenTitle = "",
                                addNotescreenContent = ""
                            )
                        }


                    }else{
                        TODO("Update the todo with the id")
                    }

                }

            }
            is AddNoteScreenEvent.titleChanged ->
            {

                _addnotescreenstate.update {
                    it.copy(
                        addnotescreeenTitle = e.v
                    )
                }
            }
        }

    }
}