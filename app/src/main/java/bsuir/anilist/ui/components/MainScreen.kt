package bsuir.anilist.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import bsuir.anilist.favorites_page.ui.FavoritesScreen
import bsuir.anilist.favorites_page.viewmodel.FavoritesViewModel
import bsuir.anilist.list_page.ui.ListScreen
import bsuir.anilist.list_page.viewmodel.ListViewModel
import bsuir.anilist.navigation.Screen

@Composable
fun MainScreen(listViewModel: ListViewModel, favoritesViewModel: FavoritesViewModel, navController: NavController) {
    val bottomNavController = rememberNavController()

    Scaffold(
        topBar = { TopBar() },
        bottomBar = { BottomNavigationBar(bottomNavController) }
    ) { paddingValues ->
        NavHost(
            navController = bottomNavController,
            startDestination = Screen.LIST.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(Screen.LIST.route) { ListScreen(listViewModel, favoritesViewModel) }
            composable(Screen.FAVORITES.route) { FavoritesScreen(favoritesViewModel) }
            composable(Screen.PROFILE.route) { ProfileScreen() }
        }
    }
}
