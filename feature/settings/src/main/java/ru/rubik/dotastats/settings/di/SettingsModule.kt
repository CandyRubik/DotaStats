package ru.rubik.dotastats.settings.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.rubik.dotastats.di.viewmodel.ViewModelKey
import ru.rubik.dotastats.settings.presentation.SettingsViewModel

@Module
interface SettingsBindsModule {

    @Binds
    @IntoMap
    @ViewModelKey(SettingsViewModel::class)
    fun bindSettingsViewModel(viewModel: SettingsViewModel): ViewModel
}