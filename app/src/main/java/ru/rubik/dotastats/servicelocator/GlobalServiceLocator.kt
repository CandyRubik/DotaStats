package ru.rubik.dotastats.servicelocator

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import ru.rubik.dotastats.heroes.data.api.HeroesApi
import ru.rubik.dotastats.heroes.data.mappers.HeroesMapper
import ru.rubik.dotastats.heroes.data.repository.HeroRepositoryImpl
import ru.rubik.dotastats.heroes.detail.data.api.HeroesLoreApi
import ru.rubik.dotastats.heroes.detail.data.repository.HeroesLoreRepositoryImpl
import ru.rubik.dotastats.heroes.detail.domain.repository.HeroesLoreRepository
import ru.rubik.dotastats.heroes.detail.domain.usecases.HeroesLoreUseCase
import ru.rubik.dotastats.heroes_api.domain.repository.HeroRepository
import ru.rubik.dotastats.network.retrofit.RetrofitFactory
import ru.rubik.dotastats.night_mode.repository.NightModeLocalRepository
import ru.rubik.dotastats.night_mode_api.domain.repository.NightModeRepository
import ru.rubik.dotastats.night_mode_api.domain.usecase.NightModeUseCase
import ru.rubik.dotastats.notes.all.data.dao.NotesDao
import ru.rubik.dotastats.notes.all.data.datasource.NotesDatabase
import ru.rubik.dotastats.notes.all.data.mappers.NotesMapper
import ru.rubik.dotastats.notes.all.data.repository.NotesLocalRepository
import ru.rubik.dotastats.notes.all.domain.repository.NotesRepository
import ru.rubik.dotastats.profile.data.api.MatchesApi
import ru.rubik.dotastats.profile.data.api.ProfileStatApi
import ru.rubik.dotastats.profile.data.mappers.MatchesMapper
import ru.rubik.dotastats.profile.data.mappers.ProfileStatsMapper
import ru.rubik.dotastats.profile.data.repository.MatchesRepositoryImpl
import ru.rubik.dotastats.profile.data.repository.ProfileStatsRepositoryImpl
import ru.rubik.dotastats.profile.domain.repository.MatchesRepository
import ru.rubik.dotastats.profile.domain.repository.ProfileStatsRepository
import ru.rubik.dotastats.profile.domain.usecases.MatchesUseCase
import ru.rubik.dotastats.profile_api.domain.repository.ProfileRepository
import ru.rubik.dotastats.profile_api.domain.usecases.ProfileUseCase
import ru.rubik.dotastats.profile_id.data.repository.ProfileIdLocalRepository
import ru.rubik.dotastats.profile_id_api.domain.repository.ProfileIdRepository
import ru.rubik.dotastats.profile_id_api.domain.usecase.ProfileIdUseCase
import ru.rubik.dotastats.profile_impl.data.api.ProfileApi
import ru.rubik.dotastats.profile_impl.data.mappers.ProfileMapper
import ru.rubik.dotastats.profile_impl.data.repository.ProfileRepositoryImpl

object GlobalServiceLocator {

    private lateinit var context: Context

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

    fun provideProfileIdUseCase(): ProfileIdUseCase {
        return ProfileIdUseCase(
            repository = provideProfileIdRepository()
        )
    }

    fun provideProfileIdRepository(): ProfileIdRepository {
        return ProfileIdLocalRepository(
            context
        )
    }

    fun provideNightModeUseCase(): NightModeUseCase {
        return NightModeUseCase(
            repository = provideNightModeRepository()
        )
    }

    fun provideNightModeRepository(): NightModeRepository {
        return NightModeLocalRepository(
            context
        )
    }

    fun provideProfileRepository(): ProfileRepository {
        return ProfileRepositoryImpl(
            webApi = RetrofitFactory.get.create(ProfileApi::class.java),
            mapper = ProfileMapper(),
        )
    }

    fun provideProfileUseCase(): ProfileUseCase {
        return ProfileUseCase(
            profileRepository = provideProfileRepository()
        )
    }

    fun provideMatchesUseCase(): MatchesUseCase {
        return MatchesUseCase(
            matchesRepository = provideMatchesRepository(),
            heroesRepository = provideHeroesRepository()
        )
    }

    fun provideHeroesRepository(): HeroRepository {
        return HeroRepositoryImpl(
            mapper = HeroesMapper(),
            webApi = RetrofitFactory.get.create(HeroesApi::class.java),
        )
    }

    private fun provideMatchesRepository(): MatchesRepository {
        return MatchesRepositoryImpl(
            mapper = MatchesMapper(),
            webApi = RetrofitFactory.get.create(MatchesApi::class.java),
        )
    }

    fun provideProfileStatsRepository(): ProfileStatsRepository {
        return ProfileStatsRepositoryImpl(
            mapper = ProfileStatsMapper(),
            webApi = RetrofitFactory.get.create(ProfileStatApi::class.java)
        )
    }

    fun provideHeroesLoreUseCase(): HeroesLoreUseCase {
        return HeroesLoreUseCase(
            repository = provideHeroesLoreRepository()
        )
    }

    private fun provideHeroesLoreRepository(): HeroesLoreRepository {
        return HeroesLoreRepositoryImpl(
            webApi = RetrofitFactory.get.create(HeroesLoreApi::class.java)
        )
    }
}