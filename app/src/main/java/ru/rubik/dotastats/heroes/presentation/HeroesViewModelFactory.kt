package ru.rubik.dotastats.heroes.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.rubik.dotastats.heroes.data.repository.HeroesRepositoryLocal

@Suppress("UNCHECKED_CAST")
class HeroesViewModelFactory(private val repository: HeroesRepositoryLocal)
    : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(HeroesViewModel::class.java)) {
            return HeroesViewModel(repository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}