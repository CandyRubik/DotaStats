plugins {
    id("android-library-convention")
    id("kotlin-kapt")
}

android {
    namespace = "com.example.profile_api"
}

dependencies {

    // Dagger
    implementation(libs.dagger)
    kapt(libs.daggerCompiler)
}