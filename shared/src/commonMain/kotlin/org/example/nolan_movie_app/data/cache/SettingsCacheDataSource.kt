package org.example.nolan_movie_app.data.cache

import com.russhwolf.settings.Settings
import kotlinx.serialization.builtins.ListSerializer
import org.example.nolan_movie_app.domain.model.Movie
import org.example.nolan_movie_app.utils.sharedJson

class SettingsCacheDataSource(private val settings: Settings) : CacheDataSource {
    private val trendingKey = "cached_movies"
    private val trendingTimeKey = "cached_movies_timestamp"
    private val detailPrefix = "movie_detail_" // key prefix for individual movies

    override suspend fun saveTrendingMovies(movies: List<Movie>, timestamp: Long) {
        val jsonString = sharedJson.encodeToString(ListSerializer(Movie.serializer()), movies)
        settings.putString(trendingKey, jsonString)
        settings.putLong(trendingTimeKey, timestamp)
    }

    override suspend fun getTrendingMovies(): List<Movie>? {
        return settings.getStringOrNull(trendingKey)?.let {
            runCatching { sharedJson.decodeFromString(ListSerializer(Movie.serializer()), it) }.getOrNull()
        }
    }

    override suspend fun getTrendingMoviesTimestamp(): Long? {
        return settings.getLongOrNull(trendingTimeKey)
    }

    override suspend fun saveMovieDetail(movie: Movie) {
        settings.putString("$detailPrefix${movie.id}", sharedJson.encodeToString(Movie.serializer(), movie))
    }

    override suspend fun getMovieDetail(id: Int): Movie? {
        return settings.getStringOrNull("$detailPrefix$id")?.let {
            runCatching { sharedJson.decodeFromString<Movie>(it) }.getOrNull()
        }
    }
}