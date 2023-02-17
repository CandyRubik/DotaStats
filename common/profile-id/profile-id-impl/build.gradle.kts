plugins {
    id("android-library-convention")
    id("kotlin-kapt")
}

android {
    namespace = "ru.rubik.dotastats.profile_id"
}

dependencies {

    implementation(libs.androidCore)
    implementation(libs.appCompat)

    // Dagger
    implementation(libs.dagger)
    kapt(libs.daggerCompiler)

    implementation(project(Modules.Common.PROFILE_ID_API))
}