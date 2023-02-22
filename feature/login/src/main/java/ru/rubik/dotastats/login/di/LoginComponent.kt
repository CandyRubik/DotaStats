package ru.rubik.dotastats.login.di

import dagger.Component
import ru.rubik.dotastats.di.FeatureScope
import ru.rubik.dotastats.login.presentation.ui.LoginFragment

@FeatureScope
@Component(
    modules = [LoginBindsModule::class],
    dependencies = [LoginExternalDependencies::class]
)
interface LoginComponent {

    @Component.Builder
    interface Builder {

        fun build(): LoginComponent
        fun create(dependencies: LoginExternalDependencies): Builder
    }

    fun inject(loginFragment: LoginFragment)
}