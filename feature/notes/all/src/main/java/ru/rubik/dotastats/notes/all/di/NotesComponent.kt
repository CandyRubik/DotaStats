package ru.rubik.dotastats.notes.all.di

import dagger.Component
import ru.rubik.dotastats.di.FeatureScope
import ru.rubik.dotastats.notes.all.presentation.ui.NotesFragment

@FeatureScope
@Component(
    modules = [NotesScreenBindsModule::class],
    dependencies = [NotesExternalDependencies::class]
)
interface NotesComponent {

    @Component.Builder
    interface Builder {

        fun build(): NotesComponent
        fun create(dependencies: NotesExternalDependencies): Builder
    }

    fun inject(notesFragment: NotesFragment)
}