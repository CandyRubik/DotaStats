package ru.rubik.dotastats.splash.di

import android.content.Context
import ru.rubik.dotastats.di.dependency.FeatureExternalDependencies
import ru.rubik.dotastats.night_mode_api.domain.usecase.NightModeUseCase
import ru.rubik.dotastats.profile_id_api.domain.usecase.ProfileIdUseCase

interface SplashExternalDependencies : FeatureExternalDependencies {

    val context: Context
    val splashNavigation: SplashNavigation
    val profileIdUseCase: ProfileIdUseCase
    val nightModeUseCase: NightModeUseCase
}