plugins {
    id("android-library-convention")
    id("kotlin-parcelize")
}

android {
    namespace = "ru.rubik.dotastats.design"
}

dependencies {

    implementation(libs.material)
    implementation(libs.appCompat)
    implementation(libs.androidCore)
}