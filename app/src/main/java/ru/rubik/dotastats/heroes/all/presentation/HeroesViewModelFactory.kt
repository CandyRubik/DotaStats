package ru.rubik.dotastats.heroes.all.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.rubik.dotastats.shared.heroes.domain.repository.HeroRepository

@Suppress("UNCHECKED_CAST")
class HeroesViewModelFactory(private val repository: HeroRepository)
    : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(HeroesViewModel::class.java)) {
            return HeroesViewModel(repository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}