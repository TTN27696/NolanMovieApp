package org.example.nolan_movie_app.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import org.example.nolan_movie_app.viewModel.MovieViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun MovieListScreen(viewModel: MovieViewModel = koinViewModel()) {
    val movies by viewModel.movies.collectAsState()
    var searchText by remember { mutableStateOf(TextFieldValue("")) }

    LaunchedEffect(Unit) {
        viewModel.loadTrending()
    }

    Column(modifier = Modifier.padding(16.dp)) {
        BasicTextField(
            value = searchText,
            onValueChange = {
                searchText = it
                if (it.text.isBlank()) viewModel.loadTrending()
                else viewModel.search(it.text)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(text = if (searchText.text.isEmpty()) "Trending Movies" else "Search Results")

        Spacer(modifier = Modifier.height(8.dp))

        movies.forEach { movie ->
            Row(modifier = Modifier.padding(vertical = 8.dp)) {
                AsyncImage(
                    model = movie.posterUrl,
                    contentDescription = null,
                    modifier = Modifier.size(80.dp),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(text = movie.title)
                    Text(text = "Year: ${movie.releaseYear}")
                    Text(text = "Rating: ${movie.voteAverage}")
                }
            }
        }
    }
}