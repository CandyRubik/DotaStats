plugins {
    id(libs.plugins.libraryConvention.get().pluginId)
    id(libs.plugins.kotlinKapt.get().pluginId)
    id(libs.plugins.kotlinParcelize.get().pluginId)
}

android {
    namespace = "ru.rubik.dotastats.notes_api"
}

dependencies {
    implementation(libs.appCompat)

    // Dagger
    implementation(libs.dagger)
    kapt(libs.daggerCompiler)
}