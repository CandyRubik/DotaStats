package ru.rubik.dotastats.di

import androidx.annotation.IdRes
import androidx.annotation.NavigationRes
import androidx.navigation.NavDirections
import dagger.Module
import dagger.Provides
import ru.rubik.dotastats.R
import ru.rubik.dotastats.heroes.all.di.HeroesNavigation
import ru.rubik.dotastats.heroes.all.presentation.ui.HeroesFragmentDirections
import ru.rubik.dotastats.login.di.LoginNavigation
import ru.rubik.dotastats.main.di.MainNavigation
import ru.rubik.dotastats.notes.all.di.NotesNavigation
import ru.rubik.dotastats.notes.all.presentation.ui.NotesFragmentDirections
import ru.rubik.dotastats.notes_api.domain.models.Note
import ru.rubik.dotastats.profile.di.ProfileNavigation
import ru.rubik.dotastats.splash.di.SplashNavigation

@Module
class NavigationModule {

    @Provides
    fun provideSplashNavigation(): SplashNavigation {
        return object : SplashNavigation {
            override val appGraphResource = R.navigation.app_graph
            override val mainFragmentResource = R.id.mainFragment
            override val authGraphResource = R.id.auth_graph
            override val activityNavHost = R.id.activity_root__fragment__nav_host
        }
    }

    @Provides
    fun provideLoginNavigation(): LoginNavigation {
        return object : LoginNavigation {
            override val mainFragmentResource = R.id.mainFragment
            override val authGraphIdResource = R.id.auth_graph
        }
    }

    @Provides
    fun provideProfileNavigation(): ProfileNavigation {
        return object : ProfileNavigation {
            override val actionProfileFragmentToSettingsFragment =
                R.id.action_profileFragment_to_settingsFragment
            override val actionMainFragmentToAuthGraph = R.id.action_mainFragment_to_auth_graph
            override val activityNavHost = R.id.activity_root__fragment__nav_host
        }
    }

    @Provides
    fun provideHeroesNavigation(): HeroesNavigation {
        return object : HeroesNavigation {
            override fun navigateToHeroDetail(name: String): NavDirections {
                return HeroesFragmentDirections.actionHeroesStatsFragmentToHeroLoreFragment(name)
            }
        }
    }

    @Provides
    fun provideNotesNavigation(): NotesNavigation {
        return object : NotesNavigation {
            override fun navigateToNoteDetail(note: Note?): NavDirections {
                return NotesFragmentDirections.actionNotesFragmentToEditNoteFragment(note)
            }
        }
    }

    @Provides
    fun provideMainNavigation(): MainNavigation {
        return object : MainNavigation {
            @get:NavigationRes
            override val heroesGraphResource = R.navigation.heroes_graph

            @get:NavigationRes
            override val profileGraphResource = R.navigation.profile_graph

            @get:NavigationRes
            override val notesGraphResource = R.navigation.notes_graph

            @get:IdRes
            override val activityNavHost = R.id.activity_root__fragment__nav_host
        }
    }
}