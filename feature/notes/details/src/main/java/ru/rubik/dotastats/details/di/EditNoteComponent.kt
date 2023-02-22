package ru.rubik.dotastats.details.di

import dagger.Component
import ru.rubik.dotastats.details.presentation.ui.EditNoteFragment
import ru.rubik.dotastats.di.FeatureScope

@FeatureScope
@Component(
    dependencies = [EditNoteExternalDependencies::class]
)
interface EditNoteComponent {

    @Component.Builder
    interface Builder {

        fun build(): EditNoteComponent
        fun create(dependencies: EditNoteExternalDependencies): Builder
    }

    fun inject(editNoteFragment: EditNoteFragment)
}