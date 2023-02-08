package ru.rubik.dotastats.splash.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.rubik.dotastats.di.ViewModelKey
import ru.rubik.dotastats.night_mode.di.NightModeModule
import ru.rubik.dotastats.profile_id.di.ProfileIdModule
import ru.rubik.dotastats.splash.presentation.SplashViewModel

@Module(
    includes = [ProfileIdModule::class, NightModeModule::class]
)
interface SplashBindsModule {

    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    fun bindSplashViewModel(viewModel: SplashViewModel): ViewModel
}