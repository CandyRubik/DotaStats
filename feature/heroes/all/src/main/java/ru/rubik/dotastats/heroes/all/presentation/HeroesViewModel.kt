package ru.rubik.dotastats.heroes.all.presentation

import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.rubik.dotastats.heroes.all.di.HeroesNavigation
import ru.rubik.dotastats.heroes_api.domain.models.Hero
import ru.rubik.dotastats.heroes_api.domain.usecases.HeroesUseCase
import ru.rubik.dotastats.presentation.vm.ProgressBaseViewModel
import javax.inject.Inject

class HeroesViewModel @Inject constructor(
    private val heroesUseCase: HeroesUseCase,
    private val heroesNavigation: HeroesNavigation,
) : ProgressBaseViewModel() {

    private val _list: MutableStateFlow<List<Hero>> = MutableStateFlow(emptyList())
    val list: StateFlow<List<Hero>> = _list

    init {
        fetchData()
    }

    fun fetchData() {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            enableLoading()
            _list.value = heroesUseCase.getHeroes()
            disableLoading()
        }
    }

    fun navigateToDetail(navController: NavController, name: String) {
        heroesNavigation.navigateToHeroDetail(name, navController)
    }
}