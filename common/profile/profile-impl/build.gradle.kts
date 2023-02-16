plugins {
    id("android-library-convention")
    id("kotlin-kapt")
    id("kotlinx-serialization")
}

android {
    namespace = "ru.rubik.dotastats.profile_impl"
}

dependencies {

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.kotlinxSerializationJson)

    // Dagger
    implementation(libs.dagger)
    kapt(libs.daggerCompiler)

    implementation(project(Modules.NETWORK))
    implementation(project(Modules.Common.PROFILE_API))
}