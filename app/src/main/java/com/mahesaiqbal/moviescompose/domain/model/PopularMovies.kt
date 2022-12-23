package com.mahesaiqbal.moviescompose.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PopularMovies(
    val id: Int,
    val backdropPath: String,
    val originalLanguage: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val voteAverage: Double,
    val voteCount: Int,
    val isFavorite: Boolean
): Parcelable