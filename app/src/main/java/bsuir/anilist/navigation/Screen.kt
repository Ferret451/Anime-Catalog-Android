package bsuir.anilist.navigation

enum class Screen(val route: String) {
    AUTH("auth"),
    MAIN("main"),
    LIST("list"),
    ANIME_GRID("anime_grid"),
    ANIME("anime"),
    FAVORITES("favorites"),
    PROFILE("profile")
}