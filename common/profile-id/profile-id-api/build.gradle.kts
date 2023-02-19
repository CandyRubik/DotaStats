plugins {
    id("android-library-convention")
    id("kotlin-kapt")
}

android {
    namespace = "ru.rubik.dotastats.profile_id_api"
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