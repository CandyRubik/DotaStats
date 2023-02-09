package ru.rubik.dotastats.heroes.all.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.rubik.dotastats.di.viewmodel.ViewModelKey
import ru.rubik.dotastats.heroes.all.presentation.HeroesViewModel

@Module
interface HeroesBindsModule {

    @Binds
    @IntoMap
    @ViewModelKey(HeroesViewModel::class)
    fun bindHeroesViewModel(viewModel: HeroesViewModel): ViewModel
}