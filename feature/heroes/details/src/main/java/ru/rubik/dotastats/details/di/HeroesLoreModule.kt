package ru.rubik.dotastats.details.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.create
import ru.rubik.dotastats.details.data.api.HeroesLoreApi
import ru.rubik.dotastats.details.data.repository.HeroesLoreRepositoryImpl
import ru.rubik.dotastats.details.domain.repository.HeroesLoreRepository

@Module(
    includes = [HeroesLoreBindsModule::class, HeroesLoreProvidesModule::class]
)
class HeroesLoreModule

@Module
class HeroesLoreProvidesModule {

    @Provides
    fun provideHeroesLoreApi(retrofit: Retrofit): HeroesLoreApi = retrofit.create()
}

@Module
interface HeroesLoreBindsModule {

    @Binds
    fun bindsHeroesLoreRepository(heroesLoreRepositoryImpl: HeroesLoreRepositoryImpl): HeroesLoreRepository
}