plugins {
    id(libs.plugins.appConvention.get().pluginId)
    id(libs.plugins.kotlinKapt.get().pluginId)
    id(libs.plugins.navigationSafeArgs.get().pluginId)
    id(libs.plugins.firebase.crashlytics.get().pluginId)
    id(libs.plugins.google.services.get().pluginId)
}

android {
    namespace = "ru.rubik.dotastats"
}

dependencies {

    implementation(libs.bundles.androidUi)

    //Dagger
    implementation(libs.dagger)
    kapt(libs.daggerCompiler)

    //Retrofit
    implementation(libs.retrofit)

    // Firebase
    implementation(libs.firebaseCrashlytics)
    implementation(libs.firebaseAnalytics)
    implementation(libs.firebaseCore)

    implementation(project(Modules.PRESENTATION))
    implementation(project(Modules.NETWORK))
    implementation(project(Modules.DI))
    implementation(project(Modules.DESIGN))
    implementation(project(Modules.Common.HEROES_API))
    implementation(project(Modules.Common.HEROES_IMPL))
    implementation(project(Modules.Common.NIGHT_MODE_IMPL))
    implementation(project(Modules.Common.NIGHT_MODE_API))
    implementation(project(Modules.Common.PROFILE_API))
    implementation(project(Modules.Common.PROFILE_IMPL))
    implementation(project(Modules.Common.PROFILE_ID_API))
    implementation(project(Modules.Common.PROFILE_ID_IMPL))
    implementation(project(Modules.Common.NOTES_IMPL))
    implementation(project(Modules.Common.NOTES_API))
    implementation(project(Modules.Feature.SPLASH))
    implementation(project(Modules.Feature.LOGIN))
    implementation(project(Modules.Feature.PROFILE))
    implementation(project(Modules.Feature.SETTINGS))
    implementation(project(Modules.Feature.HEROES_ALL))
    implementation(project(Modules.Feature.HEROES_DETAILS))
    implementation(project(Modules.Feature.NOTES_DETAILS))
    implementation(project(Modules.Feature.NOTES_ALL))
    implementation(project(Modules.Feature.MAIN))
}

