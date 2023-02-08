package ru.rubik.dotastats.login.di

import android.content.Context
import retrofit2.Retrofit

interface LoginExternalDependencies {
    val context: Context
    val retrofit: Retrofit
}