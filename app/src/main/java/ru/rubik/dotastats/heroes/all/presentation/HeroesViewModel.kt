package ru.rubik.dotastats.heroes.all.presentation

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.rubik.dotastats.presentation.ProgressBaseViewModel
import ru.rubik.dotastats.heroes.domain.models.Hero
import ru.rubik.dotastats.heroes.domain.repository.HeroRepository

class HeroesViewModel(
    private val repository: HeroRepository,
) : ProgressBaseViewModel() {

    private val _list: MutableStateFlow<List<Hero>> = MutableStateFlow(emptyList())
    val list: StateFlow<List<Hero>> = _list

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            enableLoading()
            _list.value = repository.getHeroes()
            disableLoading()
        }
    }
}