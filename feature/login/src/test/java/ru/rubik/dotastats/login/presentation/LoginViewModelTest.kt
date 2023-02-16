package ru.rubik.dotastats.login.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import ru.rubik.dotastats.login.presentation.state.ContentState
import ru.rubik.dotastats.login.presentation.state.LoginUiState
import ru.rubik.dotastats.profile_api.domain.models.Profile
import ru.rubik.dotastats.profile_api.domain.usecases.ProfileUseCase
import ru.rubik.dotastats.profile_id_api.domain.usecase.ProfileIdUseCase

@OptIn(ExperimentalCoroutinesApi::class)
class LoginViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()
    private val dispatcher = TestCoroutineDispatcher()

    private lateinit var profileIdUseCase: ProfileIdUseCase
    private lateinit var profileUseCase: ProfileUseCase

    private lateinit var viewModel: LoginViewModel

    @Before
    fun setup() {
        profileIdUseCase = mock()
        profileUseCase = mock()
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        dispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `WHEN update login WITH not empty text EXPECT update loginUiState AND SET isLoginButtonAvailable true`() =
        runBlocking {
            val expectedIsLoginButtonAvailable = true
            val expectedText = "some text"
            val expectedState = LoginUiState(
                login = expectedText,
                contentState = ContentState.Input
            )

            createViewModel()

            viewModel.updateLogin(expectedText)

            assertEquals(expectedState, viewModel.loginUiState.value)
            assertEquals(expectedIsLoginButtonAvailable, viewModel.isLoginButtonAvailable.first())
        }

    @Test
    fun `WHEN update login WITH empty text EXPECT update loginUiState AND SET isLoginButtonAvailable false`() =
        runBlocking {
            val expectedIsLoginButtonAvailable = false
            val expectedText = ""
            val expectedState = LoginUiState(
                login = expectedText,
                contentState = ContentState.Input
            )

            createViewModel()

            viewModel.updateLogin(expectedText)

            assertEquals(expectedState, viewModel.loginUiState.value)
            assertEquals(expectedIsLoginButtonAvailable, viewModel.isLoginButtonAvailable.first())
        }

    @Test
    fun `WHEN login WITH valid profile EXPECT update loginUiState NavigateToProfile AND save steamId`() =
        runBlocking {
            val loginStub = "123"
            val profileStub = Profile(
                username = "name",
                avatarUrl = "URL",
                id = loginStub,
            )
            whenever(profileUseCase.getProfile(loginStub)).thenReturn(
                profileStub
            )
            whenever(profileIdUseCase.setSteamId(loginStub)).thenReturn(Unit)
            val expectedState = LoginUiState(
                login = loginStub,
                contentState = ContentState.NavigateToProfile
            )

            createViewModel()

            val order = inOrder(
                profileUseCase,
                profileIdUseCase,
            )

            viewModel.updateLogin(loginStub)
            viewModel.login()

            order.verify(profileUseCase).getProfile(any())
            order.verify(profileIdUseCase).setSteamId(loginStub)
            assertEquals(expectedState, viewModel.loginUiState.value)
        }

    @Test
    fun `WHEN login WITH note valid profile EXPECT update loginUiState ShowErrorToast`() =
        runBlocking {
            val loginStub = ""
            val profileStub = null
            whenever(profileUseCase.getProfile(loginStub)).thenReturn(
                profileStub
            )
            val expectedState = LoginUiState(
                login = loginStub,
                contentState = ContentState.ShowErrorToast
            )

            createViewModel()

            viewModel.updateLogin(loginStub)
            viewModel.login()

            verify(profileUseCase).getProfile(loginStub)
            assertEquals(expectedState, viewModel.loginUiState.value)
        }

    private fun createViewModel() {
        viewModel = spy(
            LoginViewModel(
                profileIdUseCase = profileIdUseCase,
                profileUseCase = profileUseCase,
            )
        )
    }
}