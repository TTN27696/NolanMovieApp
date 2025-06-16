package org.example.nolan_movie_app.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Movie (
    val id: Int,
    val title: String,
    val posterUrl: String
) {
    val formatPosterUrl: String
        get() = "https://image.tmdb.org/t/p/w500$posterUrl"
}