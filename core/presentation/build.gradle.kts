plugins {
    id("android-library-convention")
}

android {
    namespace = "ru.rubik.dotastats.presentation"
}

dependencies {

    implementation(libs.androidCore)
    implementation(libs.appCompat)
    implementation(libs.material)
    implementation(libs.fragmentKtx)
    implementation(libs.navigationUi)
    implementation(libs.navigationFragment)
    implementation(libs.lifecycleViewModel)
    implementation(libs.coroutinesAndroid)
    implementation(libs.viewBindingDelegate)

    implementation(project(Modules.DESIGN))
    implementation(libs.firebaseCrashlytics)
}