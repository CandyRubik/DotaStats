package ru.rubik.dotastats.details.presentation

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.rubik.dotastats.details.domain.usecases.HeroesLoreUseCase
import ru.rubik.dotastats.details.presentation.state.HeroLoreContentState
import ru.rubik.dotastats.details.presentation.state.HeroLoreUiState
import ru.rubik.dotastats.presentation.vm.ProgressBaseViewModel

class HeroLoreViewModel(
    private val name: String,
    private val heroesLoreUseCase: HeroesLoreUseCase
) : ProgressBaseViewModel() {


    private val _heroLoreUiState: MutableStateFlow<HeroLoreUiState> = MutableStateFlow(
        HeroLoreUiState()
    )
    val heroLoreUiState = _heroLoreUiState.asStateFlow()

    init {
        fetchData()
    }

    fun fetchData() {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            enableLoading()
            val lore = heroesLoreUseCase.getHeroLore(name)
            _heroLoreUiState.update {
                it.copy(
                    name = name,
                    lore = lore,
                    heroLoreContentState = HeroLoreContentState.Content,
                )
            }
            disableLoading()
        }
    }
}