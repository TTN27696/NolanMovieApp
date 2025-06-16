package org.example.nolan_movie_app

import org.example.nolan_movie_app.data.remote.TmdbApi
import org.koin.core.qualifier.named
import org.koin.dsl.module

val networkModule = module {
    single {
        val apiKey = get<String>(named("TMDB_API_KEY")).trim()
        println("api key: $apiKey")
        createHttpClient(apiKey = apiKey)
    }

    single {
        TmdbApi(client = get())
    }
}