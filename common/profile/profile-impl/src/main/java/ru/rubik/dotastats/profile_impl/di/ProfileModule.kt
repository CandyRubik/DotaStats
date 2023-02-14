package ru.rubik.dotastats.profile_impl.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.create
import ru.rubik.dotastats.profile_api.domain.repository.ProfileRepository
import ru.rubik.dotastats.profile_impl.data.api.ProfileApi
import ru.rubik.dotastats.profile_impl.data.repository.ProfileRepositoryImpl
import javax.inject.Singleton

@Module(
    includes = [ProfileBindsModule::class, ProfileProvidesModule::class]
)
class ProfileModule

@Module
class ProfileProvidesModule {
    @Provides
    fun provideProfileApi(retrofit: Retrofit): ProfileApi = retrofit.create()
}

@Module
interface ProfileBindsModule {

    @Binds
    fun bindProfileRepository(profileRepositoryImpl: ProfileRepositoryImpl): ProfileRepository
}