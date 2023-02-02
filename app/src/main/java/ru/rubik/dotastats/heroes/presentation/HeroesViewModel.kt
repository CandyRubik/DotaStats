package ru.rubik.dotastats.heroes.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.rubik.dotastats.shared.heroes.domain.models.Hero
import ru.rubik.dotastats.shared.heroes.domain.repository.HeroRepository

class HeroesViewModel(
    private val repository: HeroRepository,
) : ViewModel() {

    private val _list: MutableStateFlow<List<Hero>> = MutableStateFlow(emptyList())
    val list: StateFlow<List<Hero>> = _list

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {
            _list.value = repository.getHeroes()
        }
    }
}