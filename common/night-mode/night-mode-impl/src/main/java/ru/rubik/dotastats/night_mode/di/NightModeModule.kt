package ru.rubik.dotastats.night_mode.di

import dagger.Binds
import dagger.Module
import ru.rubik.dotastats.night_mode.repository.NightModeLocalRepository
import ru.rubik.dotastats.night_mode_api.domain.repository.NightModeRepository

@Module(
    includes = [NightModeBindsModule::class]
)
class NightModeModule

@Module
interface NightModeBindsModule {

    @Binds
    fun bindNightModeRepository(nightModeLocalRepository: NightModeLocalRepository): NightModeRepository
}