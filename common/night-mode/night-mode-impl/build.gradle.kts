plugins {
    id("android-library-convention")
    id("kotlin-kapt")
}

android {
    namespace = "ru.rubik.dotastats.night_mode"
}

dependencies {

    implementation(libs.androidCore)
    implementation(libs.appCompat)

    // Dagger
    implementation(libs.dagger)
    kapt(libs.daggerCompiler)

    implementation(project(Modules.Common.NIGHT_MODE_API))
}