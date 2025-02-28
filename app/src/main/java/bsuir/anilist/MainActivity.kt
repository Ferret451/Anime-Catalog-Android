package bsuir.anilist

import android.os.Bundle
import android.provider.ContactsContract.Profile
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import bsuir.anilist.auth.ui.AuthScreen
import bsuir.anilist.auth.viewmodel.AuthViewModel
import bsuir.anilist.favorites_page.viewmodel.FavoritesViewModel
import bsuir.anilist.list_page.viewmodel.ListViewModel
import bsuir.anilist.navigation.Screen
import bsuir.anilist.profile_page.viewmodel.ProfileViewModel
import bsuir.anilist.ui.components.MainScreen
import bsuir.anilist.ui.theme.AniListTheme
import com.google.firebase.auth.FirebaseAuth

class MainActivity : ComponentActivity() {
    private val authViewModel: AuthViewModel = AuthViewModel()
    private val listViewModel: ListViewModel = ListViewModel()
    private val favoritesViewModel: FavoritesViewModel = FavoritesViewModel(authViewModel, listViewModel)
    private val profileViewModel: ProfileViewModel = ProfileViewModel(authViewModel)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val auth = FirebaseAuth.getInstance()
        enableEdgeToEdge()
        setContent {
            AniListTheme {
                val navController = rememberNavController()
                NavHost(navController, startDestination = Screen.AUTH.route) {
                    composable(Screen.AUTH.route) { AuthScreen(authViewModel, navController) }
                    composable(Screen.MAIN.route) { MainScreen(authViewModel, listViewModel, favoritesViewModel, profileViewModel, navController) }
                }
            }
        }
    }
}


