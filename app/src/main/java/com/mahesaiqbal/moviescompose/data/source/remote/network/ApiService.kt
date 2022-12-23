package com.mahesaiqbal.moviescompose.data.source.remote.network

import com.mahesaiqbal.moviescompose.data.source.remote.response.popular.PopularMoviesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("api_key") apiKey: String): PopularMoviesResponse
}