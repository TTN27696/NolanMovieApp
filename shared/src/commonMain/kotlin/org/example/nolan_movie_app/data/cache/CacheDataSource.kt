package org.example.nolan_movie_app.data.cache

import org.example.nolan_movie_app.domain.model.Movie

interface CacheDataSource {
    // Trending Movies
    suspend fun saveTrendingMovies(movies: List<Movie>, timestamp: Long)
    suspend fun getTrendingMovies(): List<Movie>?
    suspend fun getTrendingMoviesTimestamp(): Long?

    // Movie Detail
    suspend fun saveMovieDetail(movie: Movie)
    suspend fun getMovieDetail(id: Int): Movie?
}