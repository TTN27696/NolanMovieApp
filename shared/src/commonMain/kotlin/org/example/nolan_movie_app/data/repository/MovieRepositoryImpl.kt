package org.example.nolan_movie_app.data.repository

import io.ktor.client.statement.bodyAsText
import org.example.nolan_movie_app.data.remote.TmdbApi
import org.example.nolan_movie_app.data.remote.model.MovieResponseDto
import org.example.nolan_movie_app.data.remote.model.toDomain
import org.example.nolan_movie_app.domain.model.Movie
import org.example.nolan_movie_app.domain.repository.MovieRepository
import org.example.nolan_movie_app.utils.Result
import org.example.nolan_movie_app.utils.safeApiCall
import org.example.nolan_movie_app.utils.sharedJson

class MovieRepositoryImpl(private val api: TmdbApi) : MovieRepository {

    override suspend fun getTrendingMovies(): Result<List<Movie>> =
        safeApiCall(
            apiCall = { api.getTrendingMovies() },
            onSuccess = { response ->
                val dto = sharedJson.decodeFromString<MovieResponseDto>(response.bodyAsText())
                dto.results?.map { it.toDomain() } ?: emptyList()
            }
        )

    override suspend fun searchMovies(query: String): Result<List<Movie>> =
        safeApiCall(
            apiCall = { api.searchMovies(query) },
            onSuccess = { response ->
                val dto = sharedJson.decodeFromString<MovieResponseDto>(response.bodyAsText())
                dto.results?.map { it.toDomain() } ?: emptyList()
            }
        )

}