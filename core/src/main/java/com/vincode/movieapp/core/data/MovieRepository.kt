package com.vincode.movieapp.core.data

import com.vincode.movieapp.core.data.source.local.LocalDataSource
import com.vincode.movieapp.core.data.source.remote.RemoteDataSource
import com.vincode.movieapp.core.data.source.remote.network.ApiResponse
import com.vincode.movieapp.core.data.source.remote.response.MovieResponse
import com.vincode.movieapp.core.domain.model.Movie
import com.vincode.movieapp.core.domain.repository.IMovieRepository
import com.vincode.movieapp.core.utils.AppExecutors
import com.vincode.movieapp.core.utils.DataMapper
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.*



class MovieRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IMovieRepository {


    override fun getAllMovies(): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<MovieResponse>>() {
            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getAllMovies().map { DataMapper.mapEntitiesToDomain(it) }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean =
                data == null || data.isEmpty()


            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getAllMovies()


            override suspend fun saveCallResult(data: List<MovieResponse>) {
                val movieList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertMovie(movieList)
            }

        }.asFlow()

    @ExperimentalCoroutinesApi
    @FlowPreview
    override fun getSearchMovie(query: BroadcastChannel<String>): Flow<Resource<List<Movie>>> {
        return query.asFlow()
            .debounce(300)
            .distinctUntilChanged()
            .filter {
                it.trim().isNotEmpty()
            }
            .mapLatest {
                when (val apiResponse = remoteDataSource.getSearchMovie(it).first()) {
                    is ApiResponse.Success -> {
                        val data = DataMapper.mapResponsesToDomain(apiResponse.data)
                        Resource.Success(data)
                    }
                    is ApiResponse.Empty -> Resource.Success(listOf())
                    is ApiResponse.Error -> Resource.Error(apiResponse.errorMessage)

                }
            }

    }

    override fun getFavoriteMovie(): Flow<List<Movie>>{
        return localDataSource.getFavoriteMovie().map { DataMapper.mapFavoriteEntitiesToDomain(it) }
    }

    override fun setFavoriteMovie(movie: Movie){
        val movieEntity = DataMapper.mapDomainToEntity(movie)
        appExecutors.diskIO().execute{ localDataSource.setFavoriteMovie(movieEntity) }
    }


    override fun isFavorite(movieId: String): Flow<Boolean> {
        return localDataSource.isFavorite(movieId)
    }


}