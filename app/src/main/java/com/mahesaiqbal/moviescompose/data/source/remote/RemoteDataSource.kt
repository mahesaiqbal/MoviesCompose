package com.mahesaiqbal.moviescompose.data.source.remote

import com.mahesaiqbal.moviescompose.BuildConfig
import com.mahesaiqbal.moviescompose.data.source.remote.network.ApiResponse
import com.mahesaiqbal.moviescompose.data.source.remote.network.ApiService
import com.mahesaiqbal.moviescompose.data.source.remote.response.popular.PopularResultsMovies
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {
    suspend fun getPopularMovies(): Flow<ApiResponse<List<PopularResultsMovies>>> {
        return flow {
            try {
                val response = apiService.getPopularMovies(BuildConfig.MOVIE_API_KEY)
                val data = response.results

                if (data.isNotEmpty()) {
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }
}
