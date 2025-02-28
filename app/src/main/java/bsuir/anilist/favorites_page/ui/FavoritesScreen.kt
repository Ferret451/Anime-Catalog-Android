package bsuir.anilist.favorites_page.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import bsuir.anilist.favorites_page.viewmodel.FavoritesViewModel

@Composable
fun FavoritesScreen(favoritesViewModel: FavoritesViewModel) {
    val favorites by favoritesViewModel.favoritesList.collectAsState()
    val errorMessage by favoritesViewModel.errorMessage.collectAsState()

    LaunchedEffect(Unit) {
        favoritesViewModel.loadUserFavorites()
    }

    if (errorMessage.isNotEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = errorMessage, color = Color.Red)
        }
    } else if (favorites.isEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(favorites) { anime ->
                    FavoriteItem(anime, onRemove = { favoritesViewModel.deleteFromFavorites() })
                }
            }
        }
    }
}