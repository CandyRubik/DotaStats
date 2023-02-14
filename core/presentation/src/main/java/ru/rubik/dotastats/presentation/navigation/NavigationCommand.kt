package ru.rubik.dotastats.presentation.navigation

import androidx.annotation.IdRes
import androidx.annotation.NavigationRes
import androidx.navigation.NavDirections

sealed interface NavigationCommand {
    data class ToDirection(val direction: NavDirections) : NavigationCommand
    data class ToDirectionGlobal(val direction: NavDirections) : NavigationCommand
    data class PopBackInclusive(@IdRes val destination: Int) : NavigationCommand
    data class SetAppGraphStartWithId(@IdRes val start: Int, @NavigationRes val graph: Int) :
        NavigationCommand
}