plugins {
    id(libs.plugins.libraryConvention.get().pluginId)
    id(libs.plugins.kotlinKapt.get().pluginId)
}

android {
    namespace = "ru.rubik.dotastats.notes.all"
}

dependencies {

    implementation(libs.bundles.androidUi)

    // Dagger
    implementation(libs.dagger)
    kapt(libs.daggerCompiler)

    implementation(project(Modules.DI))
    implementation(project(Modules.DESIGN))
    implementation(project(Modules.Common.NOTES_API))
    implementation(project(Modules.Common.PROFILE_ID_API))
}