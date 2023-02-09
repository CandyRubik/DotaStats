package ru.rubik.dotastats.profile.di

import android.content.Context
import retrofit2.Retrofit
import ru.rubik.dotastats.di.dependency.FeatureExternalDependencies
import ru.rubik.dotastats.heroes_api.domain.repository.HeroRepository
import ru.rubik.dotastats.profile_api.domain.usecases.ProfileUseCase
import ru.rubik.dotastats.profile_id_api.domain.usecase.ProfileIdUseCase

interface ProfileExternalDependencies : FeatureExternalDependencies {

    val context: Context
    val retrofit: Retrofit
    val profileNavigation: ProfileNavigation
    val profileIdUseCase: ProfileIdUseCase
    val profileUseCase: ProfileUseCase
    val heroesRepository: HeroRepository
}