package ru.rubik.dotastats.profile_id_api.domain

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import ru.rubik.dotastats.profile_id_api.domain.repository.ProfileIdRepository
import ru.rubik.dotastats.profile_id_api.domain.usecase.ProfileIdUseCase

class ProfileIdUseCaseTest {

    private lateinit var profileIdRepository: ProfileIdRepository
    private lateinit var profileIdUseCase: ProfileIdUseCase

    @Before
    fun setup() {
        profileIdRepository = mock()
        profileIdUseCase = ProfileIdUseCase(profileIdRepository)
    }

    @Test
    fun `WHEN get steam id WITH not empty storage EXPECT return non null id`() = runBlocking {
        val expectedSteamId = "123123132"
        whenever(profileIdRepository.getProfileId()).thenReturn(
            expectedSteamId
        )

        val actual = profileIdUseCase.getSteamId()

        Assert.assertEquals(expectedSteamId, actual)
    }

    @Test
    fun `WHEN get steam id WITH empty storage EXPECT return null id`() = runBlocking {
        val expectedSteamId = null
        whenever(profileIdRepository.getProfileId()).thenReturn(
            expectedSteamId
        )

        val actual = profileIdUseCase.getSteamId()

        Assert.assertEquals(expectedSteamId, actual)
    }

    @Test
    fun `WHEN set steam id WITH null EXPECT call repository set method`() = runBlocking {
        val steamId = null

        profileIdUseCase.setSteamId(steamId)

        verify(profileIdRepository).setProfileId(steamId)
    }

    @Test
    fun `WHEN set steam id WITH not null id EXPECT call repository set method`() = runBlocking {
        val steamId = "steamId"

        profileIdUseCase.setSteamId(steamId)

        verify(profileIdRepository).setProfileId(steamId)
    }
}