package ru.rubik.dotastats.heroes.detail.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


@Suppress("UNCHECKED_CAST")
class HeroLoreViewModelFactory(private val name: String)
    : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(HeroLoreViewModel::class.java)) {
            return HeroLoreViewModel(name) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}