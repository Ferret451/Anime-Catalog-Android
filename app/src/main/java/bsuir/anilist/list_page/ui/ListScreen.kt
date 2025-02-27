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
import bsuir.anilist.list_page.model.Anime
import bsuir.anilist.list_page.viewmodel.ListViewModel

@Composable
fun ListScreen(listViewModel: ListViewModel) {
    val animeList by listViewModel.animeList.collectAsState()
    val currentAnime by listViewModel.currentAnime.collectAsState()
    val errorMessage by listViewModel.errorMessage.collectAsState()

    LaunchedEffect(Unit) {
        listViewModel.loadAnimeList()
    }

    if (errorMessage.isNotEmpty()) {
        Text(text = errorMessage, color = Color.Red)
    } else if (animeList.isEmpty()) {
        CircularProgressIndicator()
    } else {
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(animeList) { anime ->
                AnimeCard(anime = anime, onClick = {  })
            }
        }
    }
}