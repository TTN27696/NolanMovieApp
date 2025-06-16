package org.example.nolan_movie_app.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import org.example.nolan_movie_app.Screen

@Composable
fun MainApp() {
    var screen by remember { mutableStateOf<Screen>(Screen.MovieList) }

    when (val current = screen) {
        is Screen.MovieList -> {
            MovieListScreen(
                onMovieClick = { movieId ->
                    screen = Screen.MovieDetail(movieId)
                }
            )
        }
        is Screen.MovieDetail -> {
            MovieDetailScreen(movieId = current.movieId, onBack = {
                screen = Screen.MovieList
            })
        }
    }
}