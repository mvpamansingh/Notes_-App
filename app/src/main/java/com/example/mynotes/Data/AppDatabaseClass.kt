package com.example.mynotes.Data

import androidx.room.Database
import androidx.room.RoomDatabase



@Database(entities = [NotesEntity::class], version=1)
abstract class AppDatabaseClass:RoomDatabase() {

    abstract fun noteDao():NotesDao
}