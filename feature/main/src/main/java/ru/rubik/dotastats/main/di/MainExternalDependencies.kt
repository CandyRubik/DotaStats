package ru.rubik.dotastats.main.di

import ru.rubik.dotastats.di.dependency.FeatureExternalDependencies

interface MainExternalDependencies : FeatureExternalDependencies {

    val mainNavigation: MainNavigation
}