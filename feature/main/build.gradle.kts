plugins {
    id(libs.plugins.libraryConvention.get().pluginId)
    id(libs.plugins.kotlinKapt.get().pluginId)
}

android {
    namespace = "ru.rubik.dotastats.main"
}

dependencies {

    implementation(libs.bundles.androidUi)

    // Dagger
    implementation(libs.dagger)
    kapt(libs.daggerCompiler)

    implementation(project(Modules.DI))

}