enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()

        mavenCentral {
            content {
                includeGroup("org.jetbrains.kotlinx")
            }
        }
    }
}

includeBuild("build-logic")

include(":app")
include(":core:network")
include(":core:presentation")
include(":core:design")
include(":common:heroes:heroes-impl")
include(":common:heroes:heroes-api")
include(":common:night-mode:night-mode-impl")
include(":common:night-mode:night-mode-api")
include(":common:profile-id:profile-id-api")
include(":common:profile-id:profile-id-impl")
include(":common:profile:profile-api")
include(":common:profile:profile-impl")
include(":core:di")
include(":feature:splash")
include(":feature:login")
include(":feature:profile")
include(":feature:settings")
include(":feature:heroes:all")
include(":feature:heroes:details")
include(":common:notes:notes-api")
include(":common:notes:notes-impl")
include(":feature:notes:all")
include(":feature:notes:details")
include(":feature:main")
