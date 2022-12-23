package com.mahesaiqbal.moviescompose.data

import com.mahesaiqbal.moviescompose.data.source.local.LocalDataSource
import com.mahesaiqbal.moviescompose.data.source.remote.RemoteDataSource
import com.mahesaiqbal.moviescompose.data.source.remote.network.ApiResponse
import com.mahesaiqbal.moviescompose.data.source.remote.response.popular.PopularResultsMovies
import com.mahesaiqbal.moviescompose.domain.model.PopularMovies
import com.mahesaiqbal.moviescompose.domain.repository.IMoviesRepository
import com.mahesaiqbal.moviescompose.utils.AppExecutors
import com.mahesaiqbal.moviescompose.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MoviesRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IMoviesRepository {
    override fun getPopularMovies(): Flow<Resource<List<PopularMovies>>> =
        object : NetworkBoundResource<List<PopularMovies>, List<PopularResultsMovies>>() {
            override fun loadFromDB(): Flow<List<PopularMovies>> =
                localDataSource.getPopularMovies().map { popularMovies ->
                    DataMapper.mapEntitiesToDomain(popularMovies)
                }

            override fun shouldFetch(data: List<PopularMovies>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<PopularResultsMovies>>> =
                remoteDataSource.getPopularMovies()

            override suspend fun saveCallResult(data: List<PopularResultsMovies>) {
                val moviesList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertPopularMovies(moviesList)
            }
        }.asFlow()

    override fun getMoviesByTitle(title: String): Flow<Resource<List<PopularMovies>>> =
        localDataSource.getMoviesByTitle(title).map { moviesByTitle ->
            Resource.Success(DataMapper.mapEntitiesToDomain(moviesByTitle))
        }

    override fun getDetailMovie(id: Int): Flow<Resource<PopularMovies>> =
        localDataSource.getDetailMovie(id).map { detailMovie ->
            Resource.Success(DataMapper.mapEntityToDomain(detailMovie))
        }

    override fun getFavoritePopularMovies(): Flow<Resource<List<PopularMovies>>> =
        localDataSource.getFavoritePopularMovies().map { favorites ->
            Resource.Success(DataMapper.mapEntitiesToDomain(favorites))
        }

    override fun setFavoritePopularMovie(movie: PopularMovies, state: Boolean) {
        val movieEntity = DataMapper.mapDomainToEntity(movie)
        appExecutors.diskIO().execute {
            localDataSource.setFavoritePopularMovies(movieEntity, state)
        }
    }
}