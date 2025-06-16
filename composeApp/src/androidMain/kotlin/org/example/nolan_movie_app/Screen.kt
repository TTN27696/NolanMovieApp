package org.example.nolan_movie_app

sealed class Screen {
    data object MovieList : Screen()
    data class MovieDetail(val movieId: Int) : Screen()
}