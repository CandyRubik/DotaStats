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
include(":common:heroes")
include(":common:night_mode")
include(":common:profile_id")
