plugins {
    id("com.android.library")
    id("android-base-convention")
    id("kotlin-android")
}

android {
    buildFeatures.viewBinding = true

    buildTypes {
        getByName(Settings.BuildTypes.Release.NAME) {
            isMinifyEnabled = Settings.BuildTypes.Release.MINIFY_ENABLED
            proguardFiles(
                getDefaultProguardFile(Settings.BuildTypes.DEFAULT_PRO_GUARD_FILE),
                Settings.BuildTypes.PROGUARD_RULES
            )
        }
        getByName(Settings.BuildTypes.Debug.NAME) {
            isMinifyEnabled = Settings.BuildTypes.Debug.MINIFY_ENABLED
            proguardFiles(
                getDefaultProguardFile(Settings.BuildTypes.DEFAULT_PRO_GUARD_FILE),
                Settings.BuildTypes.PROGUARD_RULES
            )
        }
    }
}