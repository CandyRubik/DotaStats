plugins {
    id(libs.plugins.libraryConvention.get().pluginId)
    id(libs.plugins.kotlinKapt.get().pluginId)
}

android {
    namespace = "ru.rubik.dotastats.heroes_api"
}

dependencies {
    // Dagger
    implementation(libs.dagger)
    kapt(libs.daggerCompiler)
}