package ru.ruik.dotastats.notes_impl.di

import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.Provides
import ru.rubik.dotastats.notes_api.domain.repository.NotesRepository
import ru.ruik.dotastats.notes_impl.data.dao.NotesDao
import ru.ruik.dotastats.notes_impl.data.datasource.NotesDatabase
import ru.ruik.dotastats.notes_impl.data.repository.NotesLocalRepository

@Module(
    includes = [NotesBindsModule::class, NotesProvidesModule::class]
)
class NotesModule

@Module
class NotesProvidesModule {

    @Provides
    fun provideNotesDao(context: Context): NotesDao = NotesDatabase.getDatabase(context).noteDao()
}

@Module
interface NotesBindsModule {

    @Binds
    fun bindNotesRepository(notesLocalRepository: NotesLocalRepository): NotesRepository
}