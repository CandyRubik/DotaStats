plugins {
    id(libs.plugins.libraryConvention.get().pluginId)
    id(libs.plugins.kotlinKapt.get().pluginId)
    id(libs.plugins.kotlinxSerialization.get().pluginId)
}

android {
    namespace = "ru.rubik.dotastats.heroes.details"
}

dependencies {

    implementation(libs.bundles.androidUi)

    // Dagger
    implementation(libs.dagger)
    kapt(libs.daggerCompiler)

    // Retrofit
    implementation(libs.bundles.retrofit)

    implementation(project(Modules.DI))
    implementation(project(Modules.PRESENTATION))
    implementation(project(Modules.NETWORK))

    // Test
    testImplementation(libs.bundles.test)
}