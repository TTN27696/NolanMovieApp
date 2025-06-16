package org.example.nolan_movie_app.data.remote.model
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.example.nolan_movie_app.domain.model.Genre
import org.example.nolan_movie_app.domain.model.MovieDetail

@Serializable
data class MovieDetailDto(
    @SerialName("id")
    val id: Int,
    @SerialName("title")
    val title: String,
    @SerialName("overview")
    val overview: String,
    @SerialName("release_date")
    val releaseDate: String,
    @SerialName("vote_average")
    val voteAverage: Double,
    @SerialName("poster_path")
    val posterPath: String? = null,
    @SerialName("backdrop_path")
    val backdropPath: String? = null,
    @SerialName("runtime")
    val runtime: Int? = null,
    @SerialName("genres")
    val genres: List<GenreDto> = emptyList()
)

@Serializable
data class GenreDto(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String
)

fun MovieDetailDto.toDomain(): MovieDetail {
    return MovieDetail(
        id = id,
        title = title,
        overview = overview,
        releaseDate = releaseDate,
        voteAverage = voteAverage,
        posterPath = posterPath ?: "",
        backdropPath = backdropPath ?: "",
        runtime = runtime ?: 0,
        genres = genres.map { it.mapToGenre() }
    )
}

fun GenreDto.mapToGenre() = Genre(id, name)