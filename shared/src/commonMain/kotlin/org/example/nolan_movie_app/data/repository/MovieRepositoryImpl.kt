package org.example.nolan_movie_app.data.repository

import io.ktor.client.statement.bodyAsText
import io.ktor.util.date.getTimeMillis
import org.example.nolan_movie_app.data.cache.CacheDataSource
import org.example.nolan_movie_app.data.remote.TmdbApi
import org.example.nolan_movie_app.data.remote.model.MovieDetailDto
import org.example.nolan_movie_app.data.remote.model.MovieResponseDto
import org.example.nolan_movie_app.data.remote.model.toDomain
import org.example.nolan_movie_app.domain.model.Movie
import org.example.nolan_movie_app.domain.model.MovieDetail
import org.example.nolan_movie_app.domain.repository.MovieRepository
import org.example.nolan_movie_app.utils.Result
import org.example.nolan_movie_app.utils.safeApiCall
import org.example.nolan_movie_app.utils.sharedJson

class MovieRepositoryImpl(
    private val api: TmdbApi,
    private val cache: CacheDataSource
) : MovieRepository {

    private val timeMillisOfADay = 24 * 60 * 60 * 1000

    override suspend fun getTrendingMovies(): Result<List<Movie>> {
        val now = getTimeMillis()
        val cached = cache.getTrendingMovies()
        val timestamp = cache.getTrendingMoviesTimestamp()

        if (cached != null && timestamp != null && now - timestamp < timeMillisOfADay) {
            return Result.Success(cached)
        }

        return safeApiCall(
            apiCall = { api.getTrendingMovies() },
            onSuccess = { response ->
                val dto = sharedJson.decodeFromString<MovieResponseDto>(response.bodyAsText())
                val movies = dto.results?.map { it.toDomain() } ?: emptyList()
                cache.saveTrendingMovies(movies, now)
                movies
            }
        )
    }

    override suspend fun searchMovies(query: String): Result<List<Movie>> =
        safeApiCall(
            apiCall = { api.searchMovies(query) },
            onSuccess = { response ->
                val dto = sharedJson.decodeFromString<MovieResponseDto>(response.bodyAsText())
                dto.results?.map { it.toDomain() } ?: emptyList()
            }
        )

    override suspend fun getMovieDetail(id: Int): Result<MovieDetail> =
        safeApiCall(
            apiCall = { api.getMovieDetail(id) },
            onSuccess = { response ->
                val dto = sharedJson.decodeFromString<MovieDetailDto>(response.bodyAsText())
                dto.toDomain()
            }
        )

}