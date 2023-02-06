package ru.rubik.dotastats.heroes.detail.presentation

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.rubik.dotastats.heroes.detail.presentation.state.ContentState
import ru.rubik.dotastats.heroes.detail.presentation.state.HeroLoreUiState
import ru.rubik.dotastats.servicelocator.GlobalServiceLocator
import ru.rubik.dotastats.presentation.ProgressBaseViewModel

class HeroLoreViewModel(
    private val name: String,
): ru.rubik.dotastats.presentation.ProgressBaseViewModel()  {

    private val heroesLoreUseCase = GlobalServiceLocator.provideHeroesLoreUseCase()

    private val _heroLoreUiState: MutableStateFlow<HeroLoreUiState> = MutableStateFlow(
        HeroLoreUiState()
    )
    val heroLoreUiState = _heroLoreUiState.asStateFlow()

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            enableLoading()
            val lore = heroesLoreUseCase.getHeroLore(name)
            _heroLoreUiState.update {
                it.copy(
                    name = name,
                    lore = lore,
                    contentState = ContentState.Content,
                )
            }
            disableLoading()
        }
    }
}