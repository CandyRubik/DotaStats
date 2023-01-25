package ru.rubik.dotastats.servicelocator

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import ru.rubik.dotastats.notes.all.data.dao.NotesDao
import ru.rubik.dotastats.notes.all.data.datasource.NotesDatabase
import ru.rubik.dotastats.notes.all.data.mappers.NotesMapper
import ru.rubik.dotastats.notes.all.data.repository.NotesLocalRepository
import ru.rubik.dotastats.notes.all.domain.repository.NotesRepository
import ru.rubik.dotastats.shared.steamId.data.repository.SteamIdLocalRepository
import ru.rubik.dotastats.shared.steamId.domain.repository.SteamIdRepository
import ru.rubik.dotastats.shared.steamId.domain.usecase.GetSteamIdUseCase

object GlobalServiceLocator {

    private lateinit var context: Context

    private const val CREDENTIALS_KEY = "CREDENTIALS_KEY"

    fun initializeContext(applicationContext: Context) {
        this.context = applicationContext
    }

    fun provideNotesRepository(): NotesRepository {
        return NotesLocalRepository(
            notesDao = provideNotesDao(),
            mapper = NotesMapper(),
        )
    }

    private fun provideNotesDao(): NotesDao {
        return NotesDatabase.getDatabase(context).noteDao()
    }

    fun provideGetSteamIdUseCase(): GetSteamIdUseCase {
        return GetSteamIdUseCase(
            repository = provideSteamIdRepository()
        )
    }

    fun provideSteamIdRepository(): SteamIdRepository {
        return SteamIdLocalRepository(
            sharedPreferences = context.getSharedPreferences(
                CREDENTIALS_KEY, AppCompatActivity.MODE_PRIVATE
            )
        )
    }
}