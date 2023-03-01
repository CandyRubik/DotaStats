plugins {
    id(libs.plugins.libraryConvention.get().pluginId)
    id(libs.plugins.kotlinKapt.get().pluginId)
}

android {
    namespace = "ru.rubik.dotastats.profile_id"
}

dependencies {

    implementation(libs.androidCore)
    implementation(libs.appCompat)

    // Dagger
    implementation(libs.dagger)
    kapt(libs.daggerCompiler)

    implementation(project(Modules.Common.PROFILE_ID_API))
}