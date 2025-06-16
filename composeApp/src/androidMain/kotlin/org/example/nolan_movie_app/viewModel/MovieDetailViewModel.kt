package org.example.nolan_movie_app.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.example.nolan_movie_app.UIState
import org.example.nolan_movie_app.domain.model.MovieDetail
import org.example.nolan_movie_app.domain.usecase.GetMovieDetailUseCase
import org.example.nolan_movie_app.utils.Result

class MovieDetailViewModel(
    private val getMovieDetail: GetMovieDetailUseCase
) : ViewModel() {

    private val _movieDetail = MutableStateFlow<UIState<MovieDetail>>(UIState.Loading)
    val movieDetail: StateFlow<UIState<MovieDetail>> = _movieDetail

    fun loadMovieDetail(id: Int) {
        viewModelScope.launch {
            _movieDetail.value = UIState.Loading
            when (val result = getMovieDetail(id)) {
                is Result.Success -> _movieDetail.value = UIState.Success(result.data)
                is Result.Failure -> _movieDetail.value = UIState.Error(result.message)
            }
        }
    }

}