package com.mahesaiqbal.moviescompose.domain.usecase

import com.mahesaiqbal.moviescompose.data.Resource
import com.mahesaiqbal.moviescompose.domain.model.PopularMovies
import kotlinx.coroutines.flow.Flow

interface MoviesUseCase {
    fun getPopularMovies(): Flow<Resource<List<PopularMovies>>>
    fun getMoviesByTitle(title: String): Flow<Resource<List<PopularMovies>>>
    fun getDetailMovie(id: Int): Flow<Resource<PopularMovies>>
    fun getFavoritePopularMovies(): Flow<Resource<List<PopularMovies>>>
    fun setFavoritePopularMovie(movie: PopularMovies, state: Boolean)
}