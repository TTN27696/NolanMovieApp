package org.example.nolan_movie_app.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import org.example.nolan_movie_app.data.remote.model.MovieDto
import org.example.nolan_movie_app.data.remote.model.MovieResponseDto

class TmdbApi(
    private val client: HttpClient
) {
    suspend fun getTrendingMovies(): List<MovieDto> {
        val response: MovieResponseDto = client.get("https://api.themoviedb.org/3/trending/movie/day") {
            parameter("language", "en-US")
        }.body()

        return response.results ?: emptyList()
    }

    suspend fun searchMovies(query: String): List<MovieDto> {
        val response: MovieResponseDto = client.get("https://api.themoviedb.org/3/search/movie") {
            parameter("query", query)
            parameter("language", "en-US")
        }.body()

        return response.results ?: emptyList()
    }
}