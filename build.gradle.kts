// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "7.3.1" apply false
    id("com.android.library") version "7.3.1" apply false
    id("org.jetbrains.kotlin.android") version "1.7.20" apply false
    id("org.jetbrains.kotlin.jvm") version "1.7.21" apply false
}

buildscript {
    repositories {
        mavenCentral()
        google()
        gradlePluginPortal()
    }
    dependencies {
        classpath(libs.navigationClasspath)
        classpath(libs.kotlinxSerialization)
    }
}

allprojects {
    configurations.configureEach {
        resolutionStrategy {
            val coroutines: MinimalExternalModuleDependency =
                rootProject.libs.coroutinesAndroid.get()
            val forcedCoroutines: ModuleVersionSelector =
                org.gradle.api.internal.artifacts.DefaultModuleVersionSelector.newSelector(
                    coroutines.module,
                    coroutines.versionConstraint.requiredVersion
                )
            force(forcedCoroutines)
        }
    }
}

tasks.register("clean", Delete::class).configure {
    group = "build"
    delete(rootProject.buildDir)
}