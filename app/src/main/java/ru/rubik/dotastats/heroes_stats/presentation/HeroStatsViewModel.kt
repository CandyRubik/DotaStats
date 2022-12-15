package ru.rubik.dotastats.heroes_stats.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.rubik.dotastats.heroes_stats.data.repository.HeroesStatsLocalRepository
import ru.rubik.dotastats.heroes_stats.domain.entities.HeroStatsItem

class HeroStatsViewModel(
    private val repository: HeroesStatsLocalRepository,
) : ViewModel() {

    private val _list: MutableStateFlow<List<HeroStatsItem>> = MutableStateFlow(emptyList())
    val list: StateFlow<List<HeroStatsItem>> = _list

    init {
        viewModelScope.launch {
            _list.value = repository.getHeroes()
        }
    }
}