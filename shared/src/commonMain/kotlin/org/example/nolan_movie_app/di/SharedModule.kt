package org.example.nolan_movie_app.di

import org.example.nolan_movie_app.data.remote.TmdbApi
import org.example.nolan_movie_app.data.repository.MovieRepositoryImpl
import org.example.nolan_movie_app.domain.repository.MovieRepository
import org.example.nolan_movie_app.domain.usecase.GetTrendingMoviesUseCase
import org.example.nolan_movie_app.domain.usecase.SearchMoviesUseCase
import org.koin.core.module.Module
import org.koin.dsl.module

val sharedModule: Module = module {
    single { TmdbApi() }
    single<MovieRepository> { MovieRepositoryImpl(get()) }
    single { GetTrendingMoviesUseCase(get()) }
    single { SearchMoviesUseCase(get()) }
}