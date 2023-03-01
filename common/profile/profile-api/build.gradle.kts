plugins {
    id(libs.plugins.libraryConvention.get().pluginId)
    id(libs.plugins.kotlinKapt.get().pluginId)
}

android {
    namespace = "com.example.profile_api"
}

dependencies {

    // Dagger
    implementation(libs.dagger)
    kapt(libs.daggerCompiler)

    // Test
    testImplementation(libs.bundles.test)
}