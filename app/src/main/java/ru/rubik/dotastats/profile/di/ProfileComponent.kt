package ru.rubik.dotastats.profile.di

import dagger.Component
import ru.rubik.dotastats.profile.presentation.ui.ProfileFragment

@Component(
    modules = [ProfileScreenModule::class],
    dependencies = [ProfileExternalDependencies::class]
)
interface ProfileComponent {

    @Component.Factory
    interface Factory {

        fun create(dependencies: ProfileExternalDependencies): ProfileComponent
    }

    fun inject(profileFragment: ProfileFragment)
}