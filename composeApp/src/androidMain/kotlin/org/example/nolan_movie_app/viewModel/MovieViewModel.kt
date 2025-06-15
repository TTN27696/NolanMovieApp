package org.example.nolan_movie_app.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.example.nolan_movie_app.domain.model.Movie
import org.example.nolan_movie_app.domain.usecase.GetTrendingMoviesUseCase
import org.example.nolan_movie_app.domain.usecase.SearchMoviesUseCase

class MovieViewModel(
    private val getTrending: GetTrendingMoviesUseCase,
    private val searchMovies: SearchMoviesUseCase
) : ViewModel() {
    private val _movies = MutableStateFlow<List<Movie>>(emptyList())
    val movies: StateFlow<List<Movie>> = _movies

    fun loadTrending() {
        viewModelScope.launch {
            _movies.value = getTrending()
        }
    }

    fun search(query: String) {
        viewModelScope.launch {
            _movies.value = searchMovies(query)
        }
    }

}