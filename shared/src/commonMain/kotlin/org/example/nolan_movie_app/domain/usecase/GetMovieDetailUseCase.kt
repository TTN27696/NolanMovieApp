package org.example.nolan_movie_app.domain.usecase

import org.example.nolan_movie_app.domain.model.MovieDetail
import org.example.nolan_movie_app.domain.repository.MovieRepository
import org.example.nolan_movie_app.utils.Result

class GetMovieDetailUseCase(private val repository: MovieRepository) {
    suspend operator fun invoke(id: Int): Result<MovieDetail> = repository.getMovieDetail(id)
}