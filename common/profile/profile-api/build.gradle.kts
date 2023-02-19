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

    // Test
    testImplementation(libs.mockitoTest)
    testImplementation(libs.mockitoInlineTest)
    testImplementation(libs.mockitoKotlinTest)
    testImplementation(libs.junitTest)
    testImplementation(libs.androidArchTest)
    testImplementation(libs.coroutinesTest)
}