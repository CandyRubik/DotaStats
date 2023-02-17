object Modules {

    const val APP = ":app"
    const val NETWORK = ":core:network"
    const val PRESENTATION = ":core:presentation"
    const val DESIGN = ":core:design"
    const val DI = ":core:di"

    object Common {

        const val HEROES_IMPL = ":common:heroes:heroes-impl"
        const val HEROES_API = ":common:heroes:heroes-api"
        const val NIGHT_MODE_API = ":common:night-mode:night-mode-api"
        const val NIGHT_MODE_IMPL = ":common:night-mode:night-mode-impl"
        const val PROFILE_ID_API = ":common:profile-id:profile-id-api"
        const val PROFILE_ID_IMPL = ":common:profile-id:profile-id-impl"
        const val PROFILE_IMPL = ":common:profile:profile-impl"
        const val PROFILE_API = ":common:profile:profile-api"
        const val NOTES_API = ":common:notes:notes-api"
        const val NOTES_IMPL = ":common:notes:notes-impl"
    }

    object Feature {

        const val SPLASH = ":feature:splash"
        const val LOGIN = ":feature:login"
        const val PROFILE = ":feature:profile"
        const val SETTINGS = ":feature:settings"
        const val HEROES_ALL = ":feature:heroes:all"
        const val HEROES_DETAILS = ":feature:heroes:details"
        const val NOTES_ALL = ":feature:notes:all"
        const val NOTES_DETAILS = ":feature:notes:details"
        const val MAIN = ":feature:main"
    }
}