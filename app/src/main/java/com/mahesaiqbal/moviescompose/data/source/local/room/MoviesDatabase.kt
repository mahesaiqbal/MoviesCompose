package com.mahesaiqbal.moviescompose.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mahesaiqbal.moviescompose.data.source.local.entity.PopularMoviesEntity

@Database(entities = [
    PopularMoviesEntity::class
], version = 1, exportSchema = false)
abstract class MoviesDatabase : RoomDatabase() {
    abstract fun moviesDao(): MoviesDao
}
