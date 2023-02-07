plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-parcelize")
    id("kotlin-kapt")
    id("androidx.navigation.safeargs.kotlin")
    id("kotlinx-serialization")
}

android {
    namespace = "ru.rubik.dotastats"
    compileSdk = 33

    defaultConfig {
        applicationId = "ru.rubik.dotastats"
        minSdk = 28
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }
    buildFeatures {
        viewBinding = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.0")
    implementation("com.google.android.material:material:1.7.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.fragment:fragment-ktx:1.5.5")
    implementation("androidx.navigation:navigation-fragment-ktx:2.5.3")
    implementation("androidx.navigation:navigation-ui-ktx:2.5.3")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")
    implementation("com.github.kirich1409:viewbindingpropertydelegate-noreflection:1.5.6")

    //Room
    implementation("androidx.room:room-ktx:2.5.0")
    implementation("androidx.room:room-runtime:2.5.0")
    kapt("androidx.room:room-compiler:2.5.0")

    //Coil
    implementation("io.coil-kt:coil:2.2.2")

    //Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.2")

    implementation(project(":core:presentation"))
    implementation(project(":core:network"))
    implementation(project(":core:design"))
    implementation(project(":common:heroes:heroes-api"))
    implementation(project(":common:heroes:heroes-impl"))
    implementation(project(":common:night-mode:night-mode-impl"))
    implementation(project(":common:night-mode:night-mode-api"))
    implementation(project(":common:profile-id:profile-id-impl"))
    implementation(project(":common:profile-id:profile-id-api"))
}