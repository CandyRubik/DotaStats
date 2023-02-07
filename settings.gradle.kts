pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "DotaStats"
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
