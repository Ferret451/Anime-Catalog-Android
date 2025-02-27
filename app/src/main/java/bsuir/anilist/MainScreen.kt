package bsuir.anilist

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import bsuir.anilist.navigation.Screen

@Composable
fun MainScreen(navController: NavController) {
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
            composable(Screen.LIST.route) { ListScreen() }
            composable(Screen.FAVORITES.route) { FavoritesScreen() }
            composable(Screen.PROFILE.route) { ProfileScreen() }
        }
    }
}
