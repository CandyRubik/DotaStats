plugins {
    id("android-library-convention")
    id("kotlinx-serialization")
    id("kotlin-kapt")
}

android {
    namespace = "ru.rubik.dotastats.heroes"
}

dependencies {

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.kotlinxSerializationJson)

    // Dagger
    implementation(libs.dagger)
    kapt(libs.daggerCompiler)

    implementation(project(Modules.NETWORK))
    implementation(project(Modules.Common.HEROES_API))
}