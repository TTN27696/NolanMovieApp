package org.example.nolan_movie_app.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.statement.HttpResponse

class TmdbApi(
    private val client: HttpClient
) {
    suspend fun getTrendingMovies(): HttpResponse {
        return client.get("https://api.themoviedb.org/3/trending/movie/day") {
            parameter("language", "en-US")
        }
    }

    suspend fun searchMovies(query: String): HttpResponse {
        return client.get("https://api.themoviedb.org/3/search/movie") {
            parameter("query", query)
            parameter("language", "en-US")
        }
    }

    suspend fun getMovieDetail(id: Int): HttpResponse {
        return client.get("https://api.themoviedb.org/3/movie/$id") {
            parameter("language", "en-US")
        }
    }
}