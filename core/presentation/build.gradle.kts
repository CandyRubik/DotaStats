plugins {
    id(libs.plugins.libraryConvention.get().pluginId)
}

android {
    namespace = "ru.rubik.dotastats.presentation"
}

dependencies {

    implementation(libs.bundles.androidUi)

    implementation(project(Modules.DESIGN))
    implementation(libs.firebaseCrashlytics)
}