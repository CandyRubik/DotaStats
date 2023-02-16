package ru.rubik.dotastats.details.domain.usecases

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import ru.rubik.dotastats.details.domain.repository.HeroesLoreRepository

class HeroesLoreUseCaseTest {

    private lateinit var heroesLoreRepository: HeroesLoreRepository
    private lateinit var heroesLoreUseCase: HeroesLoreUseCase

    @Before
    fun setup() {
        heroesLoreRepository = mock()
        heroesLoreUseCase = HeroesLoreUseCase(heroesLoreRepository)
    }

    @Test
    fun `WHEN get lore by name WITH hero that has lore EXPECT return hero lore`() = runBlocking {
        val heroName = "AntiMage"
        val expectedLore = "some lore"
        val heroesLoreMap = hashMapOf(
            heroName.lowercase() to expectedLore,
            "Some hero" to "some lore",
            "some other hero" to "some lore"
        )
        whenever(heroesLoreRepository.getHeroesLore()).thenReturn(
            heroesLoreMap
        )

        val actual = heroesLoreUseCase.getHeroLore(heroName)

        assertEquals(expectedLore, actual)
    }

    @Test()
    fun `WHEN get lore by name WITH hero that hasn't lore EXPECT throw exception`() = runBlocking {
        val heroName = "AntiMage"
        val expectedFailure = true
        val expectedMessage = "${HeroesLoreUseCase::class.java} Can't get lore by name"
        val heroesLoreMap = hashMapOf(
            "Some hero" to "some lore",
            "some other hero" to "some lore"
        )
        whenever(heroesLoreRepository.getHeroesLore()).thenReturn(
            heroesLoreMap
        )

        val result = kotlin.runCatching {
            heroesLoreUseCase.getHeroLore(heroName)
        }

        assertEquals(expectedFailure, result.isFailure)
        assertEquals(expectedMessage, result.exceptionOrNull()?.message)
    }
}