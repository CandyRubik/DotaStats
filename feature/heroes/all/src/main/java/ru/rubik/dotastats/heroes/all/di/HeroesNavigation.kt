package ru.rubik.dotastats.heroes.all.di

import androidx.navigation.NavDirections

interface HeroesNavigation {

    fun navigateToHeroDetail(name: String): NavDirections
}