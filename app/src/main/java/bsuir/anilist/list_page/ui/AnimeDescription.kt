package bsuir.anilist.list_page.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import bsuir.anilist.list_page.model.Anime
import bsuir.anilist.list_page.viewmodel.ListViewModel

@Composable
fun AnimeDescription(listViewModel: ListViewModel, navController: NavController) {
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

        Button(onClick = { /*TODO*/ }) {
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