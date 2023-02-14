package ru.rubik.dotastats.login.di

import android.content.Context
import retrofit2.Retrofit
import ru.rubik.dotastats.di.dependency.FeatureExternalDependencies
import ru.rubik.dotastats.profile_api.domain.usecases.ProfileUseCase
import ru.rubik.dotastats.profile_id_api.domain.usecase.ProfileIdUseCase

interface LoginExternalDependencies : FeatureExternalDependencies {

    val context: Context
    val retrofit: Retrofit
    val loginNavigation: LoginNavigation
    val profileIdUseCase: ProfileIdUseCase
    val profileUseCase: ProfileUseCase
}