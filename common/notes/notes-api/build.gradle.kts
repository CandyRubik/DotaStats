plugins {
    id("android-library-convention")
    id("kotlin-kapt")
    id("kotlin-parcelize")
}

android {
    namespace = "ru.rubik.dotastats.notes_api"
}

dependencies {
    implementation(libs.appCompat)

    // Dagger
    implementation(libs.dagger)
    kapt(libs.daggerCompiler)
}