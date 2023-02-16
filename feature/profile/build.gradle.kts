plugins {
    id("android-library-convention")
    id("kotlin-kapt")
    id("kotlinx-serialization")

}

android {
    namespace = "ru.rubik.dotastats.profile"
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

    //Retrofit
    implementation(libs.retrofit)
    implementation(libs.kotlinxSerializationJson)

    //Coil
    implementation(libs.coil)


    implementation(project(Modules.DI))
    implementation(project(Modules.DESIGN))
    implementation(project(Modules.NETWORK))
    implementation(project(Modules.PRESENTATION))
    implementation(project(Modules.Common.PROFILE_ID_API))
    implementation(project(Modules.Common.PROFILE_API))
    implementation(project(Modules.Common.HEROES_API))
}