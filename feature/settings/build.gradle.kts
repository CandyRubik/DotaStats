plugins {
    id("android-library-convention")
    id("kotlin-kapt")
}

android {
    namespace = "ru.rubik.dotastats.settings"
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

    implementation(project(Modules.DESIGN))
    implementation(project(Modules.DI))
    implementation(project(Modules.Common.NIGHT_MODE_API))
}