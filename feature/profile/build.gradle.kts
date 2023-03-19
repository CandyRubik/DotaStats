plugins {
    id(libs.plugins.libraryConvention.get().pluginId)
    id(libs.plugins.kotlinKapt.get().pluginId)
    id(libs.plugins.kotlinxSerialization.get().pluginId)
}

android {
    namespace = "ru.rubik.dotastats.profile"
}

dependencies {

    implementation(libs.bundles.androidUi)

    // Dagger
    implementation(libs.dagger)
    kapt(libs.daggerCompiler)

    //Retrofit
    implementation(libs.bundles.retrofit)

    //Coil
    implementation(libs.coil)

    implementation(project(Modules.DI))
    implementation(project(Modules.DESIGN))
    implementation(project(Modules.NETWORK))
    implementation(project(Modules.PRESENTATION))
    implementation(project(Modules.Common.PROFILE_ID_API))
    implementation(project(Modules.Common.PROFILE_API))
    implementation(project(Modules.Common.HEROES_API))
}