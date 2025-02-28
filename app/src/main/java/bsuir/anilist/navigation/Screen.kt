package bsuir.anilist.navigation

enum class Screen(val route: String) {
    AUTH("auth"),
    MAIN("main"),
    LIST("list"),
    LIST_GRID("grid"),
    LIST_ANIME("anime"),
    FAVORITES("favorites"),
    PROFILE("profile"),
    PROFILE_MAIN("profile_main"),
    PROFILE_AUTH("profile_auth"),
    PROFILE_AVA_CHANGE("profile_ava_change"),
    PROFILE_EMAIL_CHANGE("profile_email_change"),
    PROFILE_PASSWORD_CHANGE("profile_password_change")
}