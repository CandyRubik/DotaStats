package ru.rubik.dotastats.heroes_stats.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.rubik.dotastats.heroes_stats.data.repository.HeroesStatsLocalRepository

@Suppress("UNCHECKED_CAST")
class HeroStatsViewModelFactory(private val repository: HeroesStatsLocalRepository)
    : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(HeroStatsViewModel::class.java)) {
            return HeroStatsViewModel(repository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}