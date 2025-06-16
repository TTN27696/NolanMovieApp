package org.example.nolan_movie_app.domain.repository

import org.example.nolan_movie_app.domain.model.Movie
import org.example.nolan_movie_app.domain.model.MovieDetail
import org.example.nolan_movie_app.utils.Result

interface MovieRepository {
    suspend fun getTrendingMovies(): Result<List<Movie>>
    suspend fun searchMovies(query: String): Result<List<Movie>>
    suspend fun getMovieDetail(id: Int): Result<MovieDetail>
}