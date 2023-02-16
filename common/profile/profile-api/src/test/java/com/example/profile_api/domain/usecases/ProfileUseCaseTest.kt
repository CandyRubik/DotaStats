package com.example.profile_api.domain.usecases

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import ru.rubik.dotastats.profile_api.domain.models.Profile
import ru.rubik.dotastats.profile_api.domain.repository.ProfileRepository
import ru.rubik.dotastats.profile_api.domain.usecases.ProfileUseCase

class ProfileUseCaseTest {

    private lateinit var profileRepository: ProfileRepository
    private lateinit var profileUseCase: ProfileUseCase

    @Before
    fun setup() {
        profileRepository = mock()
        profileUseCase = ProfileUseCase(profileRepository)
    }

    @Test
    fun `WHEN get profile WITH valid steamId EXPECT return non null profile`() = runBlocking {
        val steamId = "123123132"
        val expectedProfile = Profile(
            username = "username",
            avatarUrl = "URL",
            id = steamId,
        )
        whenever(profileRepository.getProfile(steamId)).thenReturn(
            expectedProfile
        )

        val actual = profileUseCase.getProfile(steamId)

        assertEquals(expectedProfile, actual)
    }

    @Test
    fun `WHEN get profile WITH invalid steamId EXPECT return null profile`() = runBlocking {
        val steamId = ""
        val expectedProfile = null
        whenever(profileRepository.getProfile(steamId)).thenReturn(
            expectedProfile
        )

        val actual = profileUseCase.getProfile(steamId)

        assertEquals(expectedProfile, actual)
    }
}