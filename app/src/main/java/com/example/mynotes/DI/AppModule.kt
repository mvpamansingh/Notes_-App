package com.example.mynotes.DI

import android.content.Context
import androidx.room.Room
import com.example.mynotes.Data.AppDatabaseClass
import com.example.mynotes.Data.NotesDao
import com.example.mynotes.Data.NotesDaoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModule {


    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context):AppDatabaseClass
    {
        return Room.databaseBuilder(
            appContext,
            AppDatabaseClass::class.java,
            "AppDatabaseClass"
        ).build()
    }


    @Provides
    @Singleton
    fun provideNoteDao(appDatabaseClass: AppDatabaseClass):NotesDao
    {
        return appDatabaseClass.noteDao()
    }




    @Provides
    @Singleton
    fun notesDaoIml(notesDao: NotesDao):NotesDaoImpl
    {
         return     NotesDaoImpl(notesDao)
    }
}