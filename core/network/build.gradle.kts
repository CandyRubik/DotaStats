plugins {
    id(libs.plugins.libraryConvention.get().pluginId)
}

android {
    namespace = "ru.rubik.dotastats.network"
}

dependencies {
    // Retrofit
    implementation(libs.bundles.networkFull)
}