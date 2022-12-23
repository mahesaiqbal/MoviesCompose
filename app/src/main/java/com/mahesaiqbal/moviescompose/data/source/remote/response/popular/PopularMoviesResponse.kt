package com.mahesaiqbal.moviescompose.data.source.remote.response.popular

import com.google.gson.annotations.SerializedName

data class PopularMoviesResponse(
    @SerializedName("results")
    var results: List<PopularResultsMovies>
)
