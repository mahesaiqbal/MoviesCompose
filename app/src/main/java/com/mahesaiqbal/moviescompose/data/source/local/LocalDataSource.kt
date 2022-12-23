package com.mahesaiqbal.moviescompose.data.source.local

import com.mahesaiqbal.moviescompose.data.source.local.entity.PopularMoviesEntity
import com.mahesaiqbal.moviescompose.data.source.local.room.MoviesDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val dao: MoviesDao) {
    fun getPopularMovies(): Flow<List<PopularMoviesEntity>> =
        dao.getPopularMovies()

    fun getMoviesByTitle(title: String): Flow<List<PopularMoviesEntity>> =
        dao.getMoviesByTitle(title)

    fun getDetailMovie(id: Int): Flow<PopularMoviesEntity> =
        dao.getDetailMovie(id)

    fun getFavoritePopularMovies(): Flow<List<PopularMoviesEntity>> =
        dao.getFavoritePopularMovies()

    suspend fun insertPopularMovies(movieList: List<PopularMoviesEntity>) =
        dao.insertPopularMovies(movieList)

    fun setFavoritePopularMovies(movie: PopularMoviesEntity, newState: Boolean) {
        movie.isFavorite = newState
        dao.updatePopularMovies(movie)
    }
}