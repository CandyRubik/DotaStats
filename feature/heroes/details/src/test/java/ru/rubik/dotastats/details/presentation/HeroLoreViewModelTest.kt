package ru.rubik.dotastats.details.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.*
import ru.rubik.dotastats.details.domain.usecases.HeroesLoreUseCase
import ru.rubik.dotastats.details.presentation.state.HeroLoreContentState
import ru.rubik.dotastats.details.presentation.state.HeroLoreUiState

@OptIn(ExperimentalCoroutinesApi::class)
class HeroLoreViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()
    private val dispatcher = TestCoroutineDispatcher()

    private lateinit var heroesLoreUseCase: HeroesLoreUseCase
    private val heroName = "AntiMage"
    private lateinit var viewModel: HeroLoreViewModel

    @Before
    fun setup() {
        heroesLoreUseCase = mock()
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        dispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `WHEN load lore EXPECT update heroLoreUiState`() =
        runBlocking {
            val lore = "some hero lore"
            val expectedState = HeroLoreUiState(
                name = heroName,
                lore = lore,
                heroLoreContentState = HeroLoreContentState.Content,
            )
            whenever(heroesLoreUseCase.getHeroLore(heroName)).thenReturn(
                lore
            )

            createViewModel()
            Assert.assertEquals(expectedState, viewModel.heroLoreUiState.value)
        }

    private fun createViewModel() {
        viewModel = spy(
            HeroLoreViewModel(
                name = heroName,
                heroesLoreUseCase
            )
        )
    }
}