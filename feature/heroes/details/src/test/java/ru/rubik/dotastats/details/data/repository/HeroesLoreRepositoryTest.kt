package ru.rubik.dotastats.details.data.repository

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import ru.rubik.dotastats.details.data.api.HeroesLoreApi
import ru.rubik.dotastats.details.domain.repository.HeroesLoreRepository
import ru.rubik.dotastats.network.response.ApiResponse

class HeroesLoreRepositoryTest {

    private lateinit var webApi: HeroesLoreApi
    private lateinit var heroesLoreRepository: HeroesLoreRepository

    @Before
    fun setup() {
        webApi = mock()
        heroesLoreRepository = HeroesLoreRepositoryImpl(webApi)
    }

    @Test
    fun `WHEN get heroes lore map EXPECT call web api method AND return map`() = runBlocking {
        val expectedMap = hashMapOf(
            "some hero" to "some lore",
            "some other hero" to "some lore",
        )
        whenever(webApi.getHeroesLore()).thenReturn(ApiResponse.Success(expectedMap))

        val actual = heroesLoreRepository.getHeroesLore()

        verify(webApi).getHeroesLore()
        assertEquals(expectedMap, actual)
    }

    @Test
    fun `WHEN get heroes lore map WITH network error EXPECT throw exception`() = runBlocking {
        val expectedFailure = true
        val expectedMessage = null
        whenever(webApi.getHeroesLore()).thenReturn(ApiResponse.Failure.ApiFailure(Exception()))

        val result = kotlin.runCatching { heroesLoreRepository.getHeroesLore() }

        verify(webApi).getHeroesLore()
        assertEquals(expectedFailure, result.isFailure)
        assertEquals(expectedMessage, result.exceptionOrNull()?.message)
    }
}