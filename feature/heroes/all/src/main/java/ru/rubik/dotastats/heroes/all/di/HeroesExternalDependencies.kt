package ru.rubik.dotastats.heroes.all.di

import retrofit2.Retrofit
import ru.rubik.dotastats.di.dependency.FeatureExternalDependencies
import ru.rubik.dotastats.heroes_api.domain.usecases.HeroesUseCase

interface HeroesExternalDependencies : FeatureExternalDependencies {

    val retrofit: Retrofit
    val heroesUseCase: HeroesUseCase
    val heroesNavigation: HeroesNavigation
}