plugins {
    id(libs.plugins.libraryConvention.get().pluginId)
    id(libs.plugins.kotlinxSerialization.get().pluginId)
    id(libs.plugins.kotlinKapt.get().pluginId)
}

android {
    namespace = "ru.rubik.dotastats.heroes"
}

dependencies {

    // Retrofit
    implementation(libs.bundles.retrofit)

    // Dagger
    implementation(libs.dagger)
    kapt(libs.daggerCompiler)

    implementation(project(Modules.NETWORK))
    implementation(project(Modules.Common.HEROES_API))
}