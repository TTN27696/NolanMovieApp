package org.example.nolan_movie_app.domain.model
import kotlinx.serialization.Serializable

@Serializable
data class MovieDetail(
    val id: Int,
    val title: String,
    val overview: String,
    val releaseDate: String,
    val voteAverage: Double,
    val backdropPath: String,
    val posterPath: String,
    val runtime: Int,
    val genres: List<Genre> = emptyList()
) {
    val fullBackdropPath: String
        get() = "https://image.tmdb.org/t/p/w500$backdropPath"

    val fullPosterPath: String
        get() = "https://image.tmdb.org/t/p/w500$posterPath"
}

@Serializable
data class Genre(
    val id: Int,
    val name: String
)