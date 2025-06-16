package org.example.nolan_movie_app.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class TmdbErrorResponse(
    val status_code: Int,
    val status_message: String,
    val success: Boolean = false
)
