package ru.rubik.dotastats.heroes.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.rubik.dotastats.heroes.data.repository.HeroesRepositoryLocal
import ru.rubik.dotastats.heroes.domain.models.Hero

class HeroesViewModel(
    private val repository: HeroesRepositoryLocal,
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