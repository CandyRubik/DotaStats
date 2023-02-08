package ru.rubik.dotastats.login.di

import dagger.Component
import ru.rubik.dotastats.login.presentation.ui.LoginFragment

@Component(
    modules = [LoginBindsModule::class],
    dependencies = [LoginExternalDependencies::class]
)
interface LoginComponent {

    @Component.Factory
    interface Factory {

        fun create(dependencies: LoginExternalDependencies): LoginComponent
    }

    fun inject(loginFragment: LoginFragment)
}