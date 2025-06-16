package org.example.nolan_movie_app.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.example.nolan_movie_app.UIState
import org.example.nolan_movie_app.domain.model.Movie
import org.example.nolan_movie_app.domain.usecase.GetTrendingMoviesUseCase
import org.example.nolan_movie_app.domain.usecase.SearchMoviesUseCase
import org.example.nolan_movie_app.utils.Result

class MovieViewModel(
    private val getTrending: GetTrendingMoviesUseCase,
    private val searchMovies: SearchMoviesUseCase
) : ViewModel() {
    private val _movies = MutableStateFlow<UIState<List<Movie>>>(UIState.Loading)
    val movies: StateFlow<UIState<List<Movie>>> = _movies

    fun loadTrending() {
        viewModelScope.launch {
            _movies.value = UIState.Loading
            when (val result = getTrending()) {
                is Result.Success -> {
                    _movies.value = UIState.Success(result.data)
                }
                is Result.Failure -> {
                    _movies.value = UIState.Error(result.message)
                }
            }
        }
    }

    fun search(query: String) {
        viewModelScope.launch {
            _movies.value = UIState.Loading
            when (val result = searchMovies(query)) {
                is Result.Success -> _movies.value = UIState.Success(result.data)
                is Result.Failure -> _movies.value = UIState.Error(result.message)
            }
        }
    }

}