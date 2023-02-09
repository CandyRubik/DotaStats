package ru.rubik.dotastats.settings.di

import android.content.Context
import ru.rubik.dotastats.di.dependency.FeatureExternalDependencies
import ru.rubik.dotastats.night_mode_api.domain.usecase.NightModeUseCase

interface SettingsExternalDependencies : FeatureExternalDependencies {

    val context: Context
    val nightModeUseCase: NightModeUseCase
}