package com.mahesaiqbal.moviescompose.utils

import com.mahesaiqbal.moviescompose.data.source.local.entity.PopularMoviesEntity
import com.mahesaiqbal.moviescompose.data.source.remote.response.popular.PopularResultsMovies
import com.mahesaiqbal.moviescompose.domain.model.PopularMovies

object DataMapper {
    fun mapResponsesToEntities(input: List<PopularResultsMovies>): List<PopularMoviesEntity> {
        val moviesList = ArrayList<PopularMoviesEntity>()
        input.map {
            val movies = PopularMoviesEntity(
                id = it.id,
                backdropPath = it.backdropPath,
                originalLanguage = it.originalLanguage,
                overview = it.overview,
                popularity = it.popularity,
                posterPath = it.posterPath,
                releaseDate = it.releaseDate,
                title = it.title,
                voteAverage = it.voteAverage,
                voteCount = it.voteCount,
                isFavorite = false
            )
            moviesList.add(movies)
        }
        return moviesList
    }

    fun mapEntitiesToDomain(input: List<PopularMoviesEntity>): List<PopularMovies> =
        input.map {
            PopularMovies(
                id = it.id,
                backdropPath = it.backdropPath.toString(),
                originalLanguage = it.originalLanguage,
                overview = it.overview,
                popularity = it.popularity,
                posterPath = it.posterPath.toString(),
                releaseDate = it.releaseDate,
                title = it.title,
                voteAverage = it.voteAverage,
                voteCount = it.voteCount,
                isFavorite = it.isFavorite
            )
        }

    fun mapEntityToDomain(input: PopularMoviesEntity): PopularMovies =
        PopularMovies(
            id = input.id,
            backdropPath = input.backdropPath.toString(),
            originalLanguage = input.originalLanguage,
            overview = input.overview,
            popularity = input.popularity,
            posterPath = input.posterPath.toString(),
            releaseDate = input.releaseDate,
            title = input.title,
            voteAverage = input.voteAverage,
            voteCount = input.voteCount,
            isFavorite = input.isFavorite
        )

    fun mapDomainToEntity(input: PopularMovies) = PopularMoviesEntity(
        id = input.id,
        backdropPath = input.backdropPath,
        originalLanguage = input.originalLanguage,
        overview = input.overview,
        popularity = input.popularity,
        posterPath = input.posterPath,
        releaseDate = input.releaseDate,
        title = input.title,
        voteAverage = input.voteAverage,
        voteCount = input.voteCount,
        isFavorite = input.isFavorite
    )
}