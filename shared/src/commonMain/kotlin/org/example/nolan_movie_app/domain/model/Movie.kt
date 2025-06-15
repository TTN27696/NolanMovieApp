package org.example.nolan_movie_app.domain.model

data class Movie (
    val id: Int,
    val title: String,
    val releaseYear: String,
    val voteAverage: Double,
    val posterUrl: String
)