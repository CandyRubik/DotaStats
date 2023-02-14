package ru.rubik.dotastats.splash.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.rubik.dotastats.di.viewmodel.ViewModelKey
import ru.rubik.dotastats.splash.presentation.SplashViewModel

@Module
interface SplashBindsModule {

    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    fun bindSplashViewModel(viewModel: SplashViewModel): ViewModel
}