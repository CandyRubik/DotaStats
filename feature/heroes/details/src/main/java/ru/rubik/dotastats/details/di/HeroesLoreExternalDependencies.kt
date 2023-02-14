package ru.rubik.dotastats.details.di

import retrofit2.Retrofit
import ru.rubik.dotastats.di.dependency.FeatureExternalDependencies

interface HeroesLoreExternalDependencies : FeatureExternalDependencies {

    val retrofit: Retrofit
}