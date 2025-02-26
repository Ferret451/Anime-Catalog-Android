package bsuir.anilist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import bsuir.anilist.auth.ui.AuthScreen
import bsuir.anilist.auth.viewmodel.AuthViewModel
import bsuir.anilist.ui.theme.AniListTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AniListTheme {
                AuthScreen()
            }
        }
    }
}


