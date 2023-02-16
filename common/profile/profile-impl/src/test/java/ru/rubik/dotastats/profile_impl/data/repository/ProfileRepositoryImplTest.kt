package ru.rubik.dotastats.profile_impl.data.repository

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import ru.rubik.dotastats.network.response.ApiResponse
import ru.rubik.dotastats.profile_api.domain.models.Profile
import ru.rubik.dotastats.profile_api.domain.repository.ProfileRepository
import ru.rubik.dotastats.profile_impl.data.api.ProfileApi
import ru.rubik.dotastats.profile_impl.data.entities.AccountInfoResponse
import ru.rubik.dotastats.profile_impl.data.entities.ProfileResponse
import ru.rubik.dotastats.profile_impl.data.mappers.ProfileMapper

class ProfileRepositoryImplTest {

    private lateinit var profileApi: ProfileApi
    private lateinit var profileRepository: ProfileRepository
    private val mapper: ProfileMapper = ProfileMapper()

    @Before
    fun setup() {
        profileApi = mock()
        profileRepository = ProfileRepositoryImpl(
            profileApi,
            mapper
        )
    }

    @Test
    fun `WHEN get profile WITH valid id EXPECT return profile`() = runBlocking {
        val username = "username"
        val url = "URL"
        val id = "0"
        val response = ProfileResponse(
            account = AccountInfoResponse(
                username = username,
                avatarUrl = url,
                id = id.toLong()
            ),
        )
        val expect = Profile(
            username = username,
            avatarUrl = url,
            id = id
        )


        whenever(profileApi.getProfile(id.toLong())).thenReturn(
            ApiResponse.Success(
                response
            )
        )

        val actual = profileRepository.getProfile(id)

        assertEquals(expect, actual)
    }

    @Test
    fun `WHEN get profile WITH invalid valid id EXPECT return null`() = runBlocking {
        val id = "0"
        val response = ProfileResponse(
            account = null,
        )
        val expect = null


        whenever(profileApi.getProfile(id.toLong())).thenReturn(
            ApiResponse.Success(
                response
            )
        )

        val actual = profileRepository.getProfile(id)

        assertEquals(expect, actual)
    }
}