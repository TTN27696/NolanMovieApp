package org.example.nolan_movie_app.di

import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.example.nolan_movie_app.BuildConfig

val configModule = module {
    single(named("TMDB_API_KEY")) { BuildConfig.TMDB_API_KEY }
}