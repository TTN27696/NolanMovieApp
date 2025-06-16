package org.example.nolan_movie_app.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import org.example.nolan_movie_app.UIState
import org.example.nolan_movie_app.domain.model.Movie
import org.example.nolan_movie_app.domain.usecase.GetTrendingMoviesUseCase
import org.example.nolan_movie_app.domain.usecase.SearchMoviesUseCase
import org.example.nolan_movie_app.utils.Result

@OptIn(FlowPreview::class)
class MovieViewModel(
    private val getTrending: GetTrendingMoviesUseCase,
    private val searchMovies: SearchMoviesUseCase
) : ViewModel() {
    private val _movies = MutableStateFlow<UIState<List<Movie>>>(UIState.Loading)
    val movies: StateFlow<UIState<List<Movie>>> = _movies

    private val _query = MutableStateFlow("")
    val query: StateFlow<String> = _query

    init {
        viewModelScope.launch {
            _query
                .debounce(500)
                .distinctUntilChanged()
                .collectLatest { query ->
                    if (query.isEmpty()) {
                        loadTrending()
                    } else {
                        search(query)
                    }
                }
        }
    }

    fun onQueryChanged(newQuery: String) {
        _query.value = newQuery
    }

    private fun loadTrending() {
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

    private fun search(query: String) {
        viewModelScope.launch {
            _movies.value = UIState.Loading
            when (val result = searchMovies(query)) {
                is Result.Success -> _movies.value = UIState.Success(result.data)
                is Result.Failure -> _movies.value = UIState.Error(result.message)
            }
        }
    }

}