package ru.rubik.dotastats.login.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.rubik.dotastats.di.ViewModelKey
import ru.rubik.dotastats.login.presentation.LoginViewModel
import ru.rubik.dotastats.profile_id.di.ProfileIdModule
import ru.rubik.dotastats.profile_impl.di.ProfileModule

@Module(
    includes = [ProfileIdModule::class, ProfileModule::class]
)
interface LoginBindsModule {

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    fun bindLoginViewModel(viewModel: LoginViewModel): ViewModel
}