plugins {
    id("android-library-convention")
    id("kotlin-kapt")
}

android {
    namespace = "ru.rubik.dotastats.login"
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

    // Dagger
    implementation(libs.dagger)
    kapt(libs.daggerCompiler)

    //Retrofit
    implementation(libs.retrofit)

    implementation(project(Modules.DI))
    implementation(project(Modules.PRESENTATION))
    implementation(project(Modules.Common.PROFILE_ID_API))
    implementation(project(Modules.Common.PROFILE_API))

    // Test
    testImplementation(libs.mockitoTest)
    testImplementation(libs.mockitoInlineTest)
    testImplementation(libs.mockitoKotlinTest)
    testImplementation(libs.junitTest)
    testImplementation(libs.androidArchTest)
    testImplementation(libs.coroutinesTest)
}