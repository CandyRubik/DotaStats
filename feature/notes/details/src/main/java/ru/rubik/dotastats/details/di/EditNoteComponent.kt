package ru.rubik.dotastats.details.di

import dagger.Component
import ru.rubik.dotastats.details.presentation.ui.EditNoteFragment
import ru.rubik.dotastats.di.FeatureScope

@FeatureScope
@Component(
    dependencies = [EditNoteExternalDependencies::class]
)
interface EditNoteComponent {

    @Component.Factory
    interface Factory {

        fun create(dependencies: EditNoteExternalDependencies): EditNoteComponent
    }

    fun inject(editNoteFragment: EditNoteFragment)
}