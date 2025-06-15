package org.example.nolan_movie_app.data.remote

import org.example.nolan_movie_app.domain.model.Movie

class TmdbApi {
    suspend fun fetchTrending(): List<Movie> {
        // Fake mock data for now
        return listOf(
            Movie(1, "Interstellar", "2014", 8.6, "https://image.tmdb.org/t/p/w500/interstellar.jpg"),
            Movie(2, "Inception", "2010", 8.7, "https://image.tmdb.org/t/p/w500/inception.jpg")
        )
    }

    suspend fun search(query: String): List<Movie> {
        // Fake mock data for now
        return listOf(
            Movie(1, "Interstellar", "2014", 8.6, "https://image.tmdb.org/t/p/w500/interstellar.jpg"),
            Movie(2, "Inception", "2010", 8.7, "https://image.tmdb.org/t/p/w500/inception.jpg")
        )
    }
}