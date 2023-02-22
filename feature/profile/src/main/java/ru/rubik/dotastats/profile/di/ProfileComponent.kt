package ru.rubik.dotastats.profile.di

import dagger.Component
import ru.rubik.dotastats.di.FeatureScope
import ru.rubik.dotastats.profile.presentation.ui.ProfileFragment

@FeatureScope
@Component(
    modules = [ProfileScreenModule::class],
    dependencies = [ProfileExternalDependencies::class]
)
interface ProfileComponent {

    @Component.Builder
    interface Builder {

        fun build(): ProfileComponent
        fun create(dependencies: ProfileExternalDependencies): Builder
    }

    fun inject(profileFragment: ProfileFragment)
}