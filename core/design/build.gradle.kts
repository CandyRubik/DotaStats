plugins {
    id(libs.plugins.libraryConvention.get().pluginId)
    id(libs.plugins.kotlinParcelize.get().pluginId)
}

android {
    namespace = "ru.rubik.dotastats.design"
}

dependencies {

    implementation(libs.material)
    implementation(libs.appCompat)
    implementation(libs.androidCore)
}