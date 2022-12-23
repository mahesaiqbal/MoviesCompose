package com.mahesaiqbal.moviescompose.ui.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mahesaiqbal.moviescompose.data.Resource
import com.mahesaiqbal.moviescompose.domain.model.PopularMovies
import com.mahesaiqbal.moviescompose.domain.usecase.MoviesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MoviesViewModel(private val useCase: MoviesUseCase) : ViewModel() {
    private val _popularMovies: MutableStateFlow<Resource<List<PopularMovies>>> =
        MutableStateFlow(Resource.Loading())
    val popularMovies: StateFlow<Resource<List<PopularMovies>>> get() = _popularMovies

    fun getPopularMovies() {
        viewModelScope.launch {
            useCase.getPopularMovies().collect { resource ->
                _popularMovies.value = resource
            }
        }
    }

    private val _title = mutableStateOf("")
    val title: State<String> get() = _title

    fun searchMovies(newTitle: String) {
        _title.value = newTitle
        viewModelScope.launch {
            useCase.getMoviesByTitle(_title.value).collect { resource ->
                _popularMovies.value = resource
            }
        }
    }
}