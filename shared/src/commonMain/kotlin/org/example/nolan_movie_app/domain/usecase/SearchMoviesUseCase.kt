package org.example.nolan_movie_app.domain.usecase

import org.example.nolan_movie_app.domain.model.Movie
import org.example.nolan_movie_app.domain.repository.MovieRepository
import org.example.nolan_movie_app.utils.Result

class SearchMoviesUseCase(private val repository: MovieRepository) {
    suspend operator fun invoke(query: String): Result<List<Movie>> = repository.searchMovies(query)
}