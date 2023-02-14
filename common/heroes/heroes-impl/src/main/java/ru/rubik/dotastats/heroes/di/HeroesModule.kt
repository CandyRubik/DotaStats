package ru.rubik.dotastats.heroes.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.create
import ru.rubik.dotastats.heroes.data.api.HeroesApi
import ru.rubik.dotastats.heroes.data.repository.HeroRepositoryImpl
import ru.rubik.dotastats.heroes_api.domain.repository.HeroRepository
import javax.inject.Singleton

@Module(
    includes = [HeroesProvidesModule::class, HeroesBindsModule::class]
)
class HeroesModule

@Module
class HeroesProvidesModule {

    @Provides
    fun provideHeroesApi(retrofit: Retrofit): HeroesApi = retrofit.create()
}

@Module
interface HeroesBindsModule {

    @Binds
    fun bindHeroesRepository(heroRepositoryImpl: HeroRepositoryImpl): HeroRepository
}