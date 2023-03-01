package ru.rubik.dotastats.di

import android.app.Activity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import dagger.Module
import dagger.Provides
import ru.rubik.dotastats.R
import ru.rubik.dotastats.heroes.all.di.HeroesNavigation
import ru.rubik.dotastats.heroes.all.presentation.ui.HeroesFragmentDirections
import ru.rubik.dotastats.login.di.LoginNavigation
import ru.rubik.dotastats.notes.all.di.NotesNavigation
import ru.rubik.dotastats.notes.all.presentation.ui.NotesFragmentDirections
import ru.rubik.dotastats.notes_api.domain.models.Note
import ru.rubik.dotastats.profile.di.ProfileNavigation
import ru.rubik.dotastats.profile.presentation.ui.ProfileFragmentDirections
import ru.rubik.dotastats.splash.di.SplashNavigation

@Module
object NavigationModule {

    @Provides
    fun provideSplashNavigation(): SplashNavigation {
        return object : SplashNavigation {
            override fun changeStartDestinationByIsSignedIn(
                activity: Activity,
                isSignedIn: Boolean
            ) {
                val navController = Navigation.findNavController(
                    activity,
                    R.id.activity_root__fragment__nav_host,
                )

                val mainGraph = navController.navInflater.inflate(R.navigation.app_graph)

                mainGraph.setStartDestination(
                    if (isSignedIn) {
                        R.id.mainFragment
                    } else {
                        R.id.auth_graph
                    }
                )

                navController.graph = mainGraph
            }
        }
    }

    @Provides
    fun provideLoginNavigation(): LoginNavigation {
        return object : LoginNavigation {
            override fun navigateToMain(navController: NavController) {
                val result = navController.popBackStack(R.id.auth_graph, true)
                if (result.not()) {
                    // we can't open new destination with this action
                    // --> we opened Auth flow from splash
                    // --> need to open main graph
                    navController.navigate(R.id.mainFragment)
                }
            }
        }
    }

    @Provides
    fun provideProfileNavigation(): ProfileNavigation {
        return object : ProfileNavigation {
            override fun navigateToSettings(navController: NavController) {
                navController
                    .navigate(ProfileFragmentDirections.actionProfileFragmentToSettingsFragment())
            }

            override fun navigateToLogin(activity: Activity) {
                activity.findNavController(R.id.activity_root__fragment__nav_host)
                    .navigate(R.id.action_mainFragment_to_auth_graph)
            }
        }
    }

    @Provides
    fun provideHeroesNavigation(): HeroesNavigation {
        return object : HeroesNavigation {
            override fun navigateToHeroDetail(name: String, navController: NavController) {
                navController.navigate(
                    HeroesFragmentDirections.actionHeroesStatsFragmentToHeroLoreFragment(name)
                )
            }
        }
    }

    @Provides
    fun provideNotesNavigation(): NotesNavigation {
        return object : NotesNavigation {
            override fun navigateToNoteDetail(note: Note?, navController: NavController) {
                navController.navigate(
                    NotesFragmentDirections.actionNotesFragmentToEditNoteFragment(note)
                )
            }
        }
    }
}