package ru.rubik.dotastats.di

import dagger.Module
import ru.rubik.dotastats.heroes.di.HeroesModule
import ru.rubik.dotastats.night_mode.di.NightModeModule
import ru.rubik.dotastats.profile_id.di.ProfileIdModule
import ru.rubik.dotastats.profile_impl.di.ProfileModule
import ru.ruik.dotastats.notes_impl.di.NotesModule

@Module(
    includes = [
        NightModeModule::class,
        ProfileIdModule::class,
        ProfileModule::class,
        HeroesModule::class,
        NotesModule::class,
    ]
)
class CommonModule