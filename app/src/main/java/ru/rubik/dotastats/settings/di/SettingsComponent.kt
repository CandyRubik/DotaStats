package ru.rubik.dotastats.settings.di

import dagger.Component
import ru.rubik.dotastats.settings.presentation.ui.SettingsFragment

@Component(
    modules = [SettingsBindsModule::class],
    dependencies = [SettingsExternalDependencies::class]
)
interface SettingsComponent {

    @Component.Factory
    interface Factory {

        fun create(dependencies: SettingsExternalDependencies): SettingsComponent
    }

    fun inject(settingsFragment: SettingsFragment)
}