plugins {
    id(libs.plugins.libraryConvention.get().pluginId)
    id(libs.plugins.kotlinKapt.get().pluginId)
}

android {
    namespace = "ru.rubik.dotastats.profile_id_api"
}

dependencies {

    // Dagger
    implementation(libs.dagger)
    kapt(libs.daggerCompiler)

    // Test
    testImplementation(libs.bundles.test)
}