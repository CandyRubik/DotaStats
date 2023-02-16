plugins {
    id("android-library-convention")
    id("kotlin-kapt")
}

android {
    namespace = "ru.rubik.dotastats.heroes_api"
}

dependencies {
    // Dagger
    implementation(libs.dagger)
    kapt(libs.daggerCompiler)
}