package com.mahesaiqbal.moviescompose.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.mahesaiqbal.moviescompose.data.source.local.entity.PopularMoviesEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MoviesDao {
    @Query("SELECT * FROM popular_movies")
    fun getPopularMovies(): Flow<List<PopularMoviesEntity>>

    @Query("SELECT * FROM popular_movies WHERE title LIKE '%' || :title || '%'")
    fun getMoviesByTitle(title: String): Flow<List<PopularMoviesEntity>>

    @Query("SELECT * FROM popular_movies WHERE id = :id")
    fun getDetailMovie(id: Int): Flow<PopularMoviesEntity>

    @Query("SELECT * FROM popular_movies WHERE isFavorite = 1")
    fun getFavoritePopularMovies(): Flow<List<PopularMoviesEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPopularMovies(movie: List<PopularMoviesEntity>)

    @Update
    fun updatePopularMovies(movie: PopularMoviesEntity)
}