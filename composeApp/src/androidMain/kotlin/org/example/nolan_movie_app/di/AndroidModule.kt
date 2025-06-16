package org.example.nolan_movie_app.di

import org.example.nolan_movie_app.viewModel.MovieDetailViewModel
import org.example.nolan_movie_app.viewModel.MovieViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val androidModule = module {
    viewModel { MovieViewModel(get(), get()) }
    viewModel { MovieDetailViewModel(get()) }
}