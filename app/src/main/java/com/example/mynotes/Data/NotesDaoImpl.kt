package com.example.mynotes.Data

import com.example.mynotes.Data.NotesDao
import com.example.mynotes.Data.NotesEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NotesDaoImpl @Inject constructor(
    private val dao:NotesDao
) {

    fun getAllNotes(): Flow<List<NotesEntity>>
    {
        return dao.getAllNotes()
    }


   suspend fun deleteNotes(notesEntity: NotesEntity)
    {
        dao.deleteNotes(notesEntity)
    }

    suspend fun insertNotes(notesEntity: NotesEntity)
    {
        dao.insertNote(notesEntity)
    }

    suspend  fun clearTable()
    {
        dao.clearAll()
    }
}