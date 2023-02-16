plugins {
    id("android-library-convention")
}

android {
    namespace = "ru.rubik.dotastats.network"
}

dependencies {
    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.kotlinxSerializationJson)
    implementation(libs.loggingInterceptor)
    implementation(libs.serializationConverter)
}