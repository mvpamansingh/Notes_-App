package com.example.mynotes.Data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface NotesDao {

    @Insert
    suspend fun insertNote(notesEntity: NotesEntity)


    @Delete
   suspend fun deleteNotes(notesEntity: NotesEntity)


    @Query("select * from NotesEntity order by title")
    fun getAllNotes():Flow<List<NotesEntity>>

    @Query("Delete from NotesEntity")
    suspend fun clearAll()
}