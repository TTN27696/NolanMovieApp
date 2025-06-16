package org.example.nolan_movie_app.data.repository

import org.example.nolan_movie_app.data.remote.TmdbApi
import org.example.nolan_movie_app.data.remote.model.toDomain
import org.example.nolan_movie_app.domain.model.Movie
import org.example.nolan_movie_app.domain.repository.MovieRepository

class MovieRepositoryImpl(private val api: TmdbApi) : MovieRepository {

    override suspend fun getTrendingMovies(): List<Movie> =
        api.getTrendingMovies().map { it.toDomain() }

    override suspend fun searchMovies(query: String): List<Movie> =
        api.searchMovies(query).map { it.toDomain() }

}