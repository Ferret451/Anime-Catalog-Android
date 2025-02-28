package bsuir.anilist.list_page.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import bsuir.anilist.auth.ui.AuthScreen
import bsuir.anilist.favorites_page.viewmodel.FavoritesViewModel
import bsuir.anilist.list_page.model.Anime
import bsuir.anilist.list_page.viewmodel.ListViewModel
import bsuir.anilist.navigation.Screen
import bsuir.anilist.ui.components.MainScreen

@Composable
fun ListScreen(listViewModel: ListViewModel, favoritesViewModel: FavoritesViewModel) {
    val navController = rememberNavController()
    NavHost(navController, startDestination = Screen.ANIME_GRID.route) {
        composable(Screen.ANIME_GRID.route) { AnimeList(listViewModel, navController) }
        composable(Screen.ANIME.route) { AnimeDescription(listViewModel, favoritesViewModel, navController) }
    }
}