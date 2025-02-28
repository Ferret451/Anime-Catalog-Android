package bsuir.anilist.profile_page.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import bsuir.anilist.auth.viewmodel.AuthViewModel
import bsuir.anilist.navigation.Screen

@Composable
fun ProfileScreen(
    authViewModel: AuthViewModel,
) {
    val user by authViewModel.user.collectAsState()

    val navController = rememberNavController()
    NavHost(navController, startDestination = Screen.PROFILE_MAIN.route) {
        composable(Screen.PROFILE_MAIN.route) { ProfileMainScreen(authViewModel) }
        composable(Screen.PROFILE_AUTH.route) { ProfileAuthScreen(authViewModel) }
        composable(Screen.PROFILE_AVA_CHANGE.route) { ProfileAvaChangeScreen(authViewModel) }
        composable(Screen.PROFILE_EMAIL_CHANGE.route) { ProfileEmailChangeScreen(authViewModel) }
        composable(Screen.PROFILE_PASSWORD_CHANGE.route) { ProfilePasswordChangeScreen(authViewModel) }
    }
}

