package ru.rubik.dotastats.profile.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import retrofit2.Retrofit
import retrofit2.create
import ru.rubik.dotastats.di.ViewModelKey
import ru.rubik.dotastats.heroes.di.HeroesModule
import ru.rubik.dotastats.profile.data.api.MatchesApi
import ru.rubik.dotastats.profile.data.api.ProfileStatApi
import ru.rubik.dotastats.profile.data.repository.MatchesRepositoryImpl
import ru.rubik.dotastats.profile.data.repository.ProfileStatsRepositoryImpl
import ru.rubik.dotastats.profile.domain.repository.MatchesRepository
import ru.rubik.dotastats.profile.domain.repository.ProfileStatsRepository
import ru.rubik.dotastats.profile.presentation.ProfileViewModel
import ru.rubik.dotastats.profile_id.di.ProfileIdModule
import ru.rubik.dotastats.profile_impl.di.ProfileModule

@Module(
    includes = [
        ProfileScreenProvideModule::class,
        ProfileModule::class,
        ProfileIdModule::class,
        ProfileScreenBindsModule::class,
        HeroesModule::class
    ]
)
class ProfileScreenModule

@Module
class ProfileScreenProvideModule {

    @Provides
    fun provideMatchesApi(retrofit: Retrofit): MatchesApi = retrofit.create()

    @Provides
    fun provideProfileStatApi(retrofit: Retrofit): ProfileStatApi = retrofit.create()
}

@Module
interface ProfileScreenBindsModule {

    @Binds
    fun bindMatchesRepository(matchesRepositoryImpl: MatchesRepositoryImpl): MatchesRepository

    @Binds
    fun bindProfileStatsRepository(profileStatsRepositoryImpl: ProfileStatsRepositoryImpl): ProfileStatsRepository

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    fun bindProfileViewModel(viewModel: ProfileViewModel): ViewModel
}