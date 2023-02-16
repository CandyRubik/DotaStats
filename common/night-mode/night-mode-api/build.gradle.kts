plugins {
    id("android-library-convention")
    id("kotlin-kapt")
}

android {
    namespace = "ru.rubik.dotastats.night_mode_api"
}

dependencies {

    // Dagger
    implementation(libs.dagger)
    kapt(libs.daggerCompiler)
}