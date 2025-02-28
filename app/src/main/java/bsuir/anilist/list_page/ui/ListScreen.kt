package bsuir.anilist.list_page.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import bsuir.anilist.favorites_page.viewmodel.FavoritesViewModel
import bsuir.anilist.list_page.viewmodel.ListViewModel
import bsuir.anilist.navigation.Screen

@Composable
fun ListScreen(listViewModel: ListViewModel, favoritesViewModel: FavoritesViewModel) {
    val navController = rememberNavController()
    NavHost(navController, startDestination = Screen.LIST_GRID.route) {
        composable(Screen.LIST_GRID.route) { AnimeList(listViewModel, navController) }
        composable(Screen.LIST_ANIME.route) { AnimeDescription(listViewModel, favoritesViewModel) }
    }
}