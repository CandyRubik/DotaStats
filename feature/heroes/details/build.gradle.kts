plugins {
    id("android-library-convention")
    id("kotlin-kapt")
    id("kotlinx-serialization")
}

android {
    namespace = "ru.rubik.dotastats.heroes.details"
}

dependencies {

    implementation(libs.androidCore)
    implementation(libs.appCompat)
    implementation(libs.material)
    implementation(libs.constraint)
    implementation(libs.fragmentKtx)
    implementation(libs.navigationUi)
    implementation(libs.navigationFragment)
    implementation(libs.lifecycleViewModel)
    implementation(libs.coroutinesAndroid)
    implementation(libs.viewBindingDelegate)
    implementation(libs.swipeToRefresh)

    // Dagger
    implementation(libs.dagger)
    kapt(libs.daggerCompiler)

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.kotlinxSerializationJson)

    implementation(project(Modules.DI))
    implementation(project(Modules.PRESENTATION))
    implementation(project(Modules.NETWORK))

    // Test
    testImplementation(libs.mockitoTest)
    testImplementation(libs.mockitoInlineTest)
    testImplementation(libs.mockitoKotlinTest)
    testImplementation(libs.junitTest)
    testImplementation(libs.androidArchTest)
    testImplementation(libs.coroutinesTest)
}