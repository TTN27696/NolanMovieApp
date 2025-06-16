package org.example.nolan_movie_app.data.remote.model
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.example.nolan_movie_app.domain.model.Movie

@Serializable
data class MovieResponseDto(
    @SerialName("results")
    val results: List<MovieDto>? = null
)

@Serializable
data class MovieDto(
    @SerialName("id")
    val id: Int? = null,
    @SerialName("title")
    val title: String? = null,
    @SerialName("poster_path")
    val posterPath: String? = null,
)

fun MovieDto.toDomain(): Movie = Movie(
    id = id ?: 0,
    title = title ?: "",
    posterUrl = posterPath ?: ""
)