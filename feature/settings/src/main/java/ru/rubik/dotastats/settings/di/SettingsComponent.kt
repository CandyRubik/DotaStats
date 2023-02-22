package ru.rubik.dotastats.settings.di

import dagger.Component
import ru.rubik.dotastats.di.FeatureScope
import ru.rubik.dotastats.settings.presentation.ui.SettingsFragment

@FeatureScope
@Component(
    modules = [SettingsBindsModule::class],
    dependencies = [SettingsExternalDependencies::class]
)
interface SettingsComponent {

    @Component.Builder
    interface Builder {

        fun build(): SettingsComponent
        fun create(dependencies: SettingsExternalDependencies): Builder
    }

    fun inject(settingsFragment: SettingsFragment)
}