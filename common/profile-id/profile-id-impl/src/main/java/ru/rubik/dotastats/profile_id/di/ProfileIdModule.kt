package ru.rubik.dotastats.profile_id.di

import dagger.Binds
import dagger.Module
import ru.rubik.dotastats.profile_id.data.repository.ProfileIdLocalRepository
import ru.rubik.dotastats.profile_id_api.domain.repository.ProfileIdRepository

@Module(
    includes = [ProfileIdBindsModule::class]
)
class ProfileIdModule

@Module
interface ProfileIdBindsModule {

    @Binds
    fun bindProfileIdRepository(profileIdLocalRepository: ProfileIdLocalRepository): ProfileIdRepository
}