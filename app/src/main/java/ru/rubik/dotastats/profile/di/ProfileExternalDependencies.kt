package ru.rubik.dotastats.profile.di

import android.content.Context
import retrofit2.Retrofit

interface ProfileExternalDependencies {
    val context: Context
    val retrofit: Retrofit
}