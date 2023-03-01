plugins {
    id(libs.plugins.libraryConvention.get().pluginId)
    id(libs.plugins.kotlinKapt.get().pluginId)
}

android {
    namespace = "ru.ruik.dotastats.notes_impl"
}

dependencies {

    implementation(libs.androidCore)
    implementation(libs.appCompat)

    //Room
    kapt(libs.roomCompiler)
    implementation(libs.roomKtx)
    implementation(libs.roomRuntime)

    // Dagger
    implementation(libs.dagger)
    kapt(libs.daggerCompiler)

    implementation(project(Modules.Common.NOTES_API))
}