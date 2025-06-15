package org.example.nolan_movie_app.domain.usecase

import org.example.nolan_movie_app.domain.model.Movie
import org.example.nolan_movie_app.domain.repository.MovieRepository

class GetTrendingMoviesUseCase(private val repository: MovieRepository) {
    suspend operator fun invoke(): List<Movie> = repository.getTrendingMovies()
}