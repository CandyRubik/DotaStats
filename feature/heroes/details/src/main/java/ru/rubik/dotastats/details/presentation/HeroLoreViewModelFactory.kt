package ru.rubik.dotastats.details.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import ru.rubik.dotastats.details.domain.usecases.HeroesLoreUseCase

class HeroLoreViewModelFactory @AssistedInject constructor(
    @Assisted("heroName") private val heroName: String,
    private val useCase: HeroesLoreUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        assert(modelClass == HeroLoreViewModel::class.java)
        return HeroLoreViewModel(heroName, useCase) as T
    }
}

@AssistedFactory
interface HeroesLoreViewModelAssistedFactory {

    fun create(@Assisted("heroName") heroName: String): HeroLoreViewModelFactory
}