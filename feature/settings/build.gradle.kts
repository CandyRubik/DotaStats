plugins {
    id(libs.plugins.libraryConvention.get().pluginId)
    id(libs.plugins.kotlinKapt.get().pluginId)
}

android {
    namespace = "ru.rubik.dotastats.settings"
}

dependencies {

    implementation(libs.bundles.androidUi)

    // Dagger
    implementation(libs.dagger)
    kapt(libs.daggerCompiler)

    implementation(project(Modules.DESIGN))
    implementation(project(Modules.DI))
    implementation(project(Modules.Common.NIGHT_MODE_API))
}