package ru.rubik.dotastats.heroes.all.di

import androidx.navigation.NavController

interface HeroesNavigation {

    fun navigateToHeroDetail(name: String, navController: NavController)
}