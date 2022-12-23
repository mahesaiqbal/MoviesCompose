package com.mahesaiqbal.moviescompose.domain.usecase

import com.mahesaiqbal.moviescompose.data.Resource
import com.mahesaiqbal.moviescompose.domain.model.PopularMovies
import com.mahesaiqbal.moviescompose.domain.repository.IMoviesRepository
import kotlinx.coroutines.flow.Flow

class MoviesInteractor(private val repository: IMoviesRepository) : MoviesUseCase {
    override fun getPopularMovies(): Flow<Resource<List<PopularMovies>>> =
        repository.getPopularMovies()

    override fun getMoviesByTitle(title: String): Flow<Resource<List<PopularMovies>>> =
        repository.getMoviesByTitle(title)

    override fun getDetailMovie(id: Int): Flow<Resource<PopularMovies>> =
        repository.getDetailMovie(id)

    override fun getFavoritePopularMovies(): Flow<Resource<List<PopularMovies>>> =
        repository.getFavoritePopularMovies()

    override fun setFavoritePopularMovie(movie: PopularMovies, state: Boolean) {
        repository.setFavoritePopularMovie(movie, state)
    }
}