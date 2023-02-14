package ru.rubik.dotastats.notes.all.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.rubik.dotastats.di.viewmodel.ViewModelKey
import ru.rubik.dotastats.notes.all.presentation.NotesViewModel

@Module
interface NotesScreenBindsModule {

    @Binds
    @IntoMap
    @ViewModelKey(NotesViewModel::class)
    fun bindNotesViewModel(viewModel: NotesViewModel): ViewModel
}