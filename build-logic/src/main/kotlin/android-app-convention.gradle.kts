plugins {
    id("com.android.application")
    id("android-base-convention")
    id("kotlin-android")
}

android {
    defaultConfig {
        versionCode = Settings.VERSION_CODE
        versionName = Settings.VERSION_NAME
        applicationId = Settings.APPLICATION_ID
    }

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