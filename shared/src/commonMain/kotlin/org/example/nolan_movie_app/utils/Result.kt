package org.example.nolan_movie_app.utils
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.serialization.json.Json
import org.example.nolan_movie_app.data.remote.model.TmdbErrorResponse

sealed class Result<out T> {
    data class Success<T>(val data: T): Result<T>()
    data class Failure(val message: String): Result<Nothing>()
}

suspend fun <T> safeApiCall(
    apiCall: suspend () -> HttpResponse,
    onSuccess: suspend (HttpResponse) -> T
): Result<T> {
    return try {
        val response = apiCall()

        if (response.status.isSuccess()) {
            val json = response.bodyAsText()
            if ("\"success\":false" in json) {
                val error = sharedJson.decodeFromString<TmdbErrorResponse>(json)
                Result.Failure(error.status_message)
            } else {
                Result.Success(onSuccess(response))
            }
        } else {
            val errorMessage = try {
                val errorJson = response.bodyAsText()
                sharedJson.decodeFromString<TmdbErrorResponse>(errorJson).status_message
            } catch (e: Exception) {
                "Unexpected error: ${response.status}"
            }
            Result.Failure(errorMessage)
        }
    } catch (e: Exception) {
        Result.Failure("Network error: $e")
    }
}

val sharedJson = Json {
    ignoreUnknownKeys = true
    isLenient = true
    prettyPrint = true
}