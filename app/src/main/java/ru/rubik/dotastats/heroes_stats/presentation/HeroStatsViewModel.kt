package ru.rubik.dotastats.heroes_stats.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.rubik.dotastats.heroes_stats.data.repository.HeroesRepositoryLocal
import ru.rubik.dotastats.heroes_stats.domain.entities.HeroStat

class HeroStatsViewModel(
    private val repository: HeroesRepositoryLocal,
) : ViewModel() {

    private val _list: MutableStateFlow<List<HeroStat>> = MutableStateFlow(emptyList())
    val list: StateFlow<List<HeroStat>> = _list

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {
            _list.value = repository.getHeroes()
        }
    }
}