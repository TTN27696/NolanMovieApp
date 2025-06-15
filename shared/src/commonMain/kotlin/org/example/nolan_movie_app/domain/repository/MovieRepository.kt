package org.example.nolan_movie_app.domain.repository

import org.example.nolan_movie_app.domain.model.Movie

interface MovieRepository {
    suspend fun getTrendingMovies(): List<Movie>
    suspend fun searchMovies(query: String): List<Movie>
}