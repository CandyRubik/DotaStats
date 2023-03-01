plugins {
    id(libs.plugins.libraryConvention.get().pluginId)
    id(libs.plugins.kotlinKapt.get().pluginId)
}

android {
    namespace = "ru.rubik.dotastats.splash"
}

kapt {
    correctErrorTypes = true
}

dependencies {

    implementation(libs.bundles.androidUi)

    // Dagger
    implementation(libs.dagger)
    kapt(libs.daggerCompiler)

    implementation(project(Modules.DI))
    implementation(project(Modules.PRESENTATION))
    implementation(project(Modules.Common.PROFILE_ID_API))
    implementation(project(Modules.Common.NIGHT_MODE_API))
}