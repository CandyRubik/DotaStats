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

    @Component.Factory
    interface Factory {

        fun create(dependencies: NotesExternalDependencies): NotesComponent
    }

    fun inject(notesFragment: NotesFragment)
}