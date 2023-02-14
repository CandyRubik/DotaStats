package ru.rubik.dotastats.di

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import ru.rubik.dotastats.network.retrofit.RetrofitFactory

@Module
class NetworkProvidesModule {

    @Provides
    fun provideRetrofit(): Retrofit = RetrofitFactory.get
}