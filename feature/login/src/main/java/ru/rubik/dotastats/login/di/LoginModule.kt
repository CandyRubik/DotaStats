package ru.rubik.dotastats.login.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.rubik.dotastats.di.viewmodel.ViewModelKey
import ru.rubik.dotastats.login.presentation.LoginViewModel

@Module
interface LoginBindsModule {

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    fun bindLoginViewModel(viewModel: LoginViewModel): ViewModel
}