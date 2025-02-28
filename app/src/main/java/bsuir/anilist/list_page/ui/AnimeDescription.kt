package bsuir.anilist.list_page.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import bsuir.anilist.favorites_page.viewmodel.FavoritesViewModel
import bsuir.anilist.list_page.viewmodel.ListViewModel
import coil.compose.rememberAsyncImagePainter

@Composable
fun AnimeDescription(listViewModel: ListViewModel, favoritesViewModel: FavoritesViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ImageSlider(listViewModel.getCurrentAnime().imagesURL)

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = listViewModel.getCurrentAnime().title,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(12.dp))

        AnimeInfoRow(label = "Description", value = listViewModel.getCurrentAnime().description)
        Spacer(modifier = Modifier.height(12.dp))
        AnimeInfoRow(label = "Studio", value = listViewModel.getCurrentAnime().studio)
        Spacer(modifier = Modifier.height(12.dp))
        AnimeInfoRow(label = "Series", value = listViewModel.getCurrentAnime().seriesAmount.toString())
        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = { favoritesViewModel.addToFavorites() }) {
            Text(text = "Add to favorites")
        }
    }
}

@Composable
fun ImageSlider(images: List<String>) {
    val pagerState = rememberPagerState(pageCount = { images.size })

    HorizontalPager(state = pagerState) { page ->
        val imagePainter: Painter = rememberAsyncImagePainter(images[page])

        Card(
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.elevatedCardElevation(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(8.dp)
        ) {
            Image(
                painter = imagePainter,
                contentDescription = "Anime Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@Composable
fun AnimeInfoRow(label: String, value: String) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "$label:",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        Text(text = value,
            fontSize = 18.sp,
            textAlign = TextAlign.Justify,
            modifier = Modifier.fillMaxWidth()
        )
    }
}