plugins {
    id(libs.plugins.libraryConvention.get().pluginId)
    id(libs.plugins.kotlinKapt.get().pluginId)
}

android {
    namespace = "ru.rubik.dotastats.di"
}

dependencies {
    implementation(libs.lifecycleViewModel)
    implementation(libs.androidCore)
    implementation(libs.appCompat)

    // Dagger
    implementation(libs.dagger)
    kapt(libs.daggerCompiler)
}