package com.vincode.movieapp.core.data.source.remote

import android.util.Log
import com.vincode.movieapp.core.data.source.remote.network.ApiResponse
import com.vincode.movieapp.core.data.source.remote.network.ApiService
import com.vincode.movieapp.core.data.source.remote.response.MovieResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {

    private val apiKey = "e96328e96ce61fcd26a32771b816d85c"

    suspend fun getAllMovies(): Flow<ApiResponse<List<MovieResponse>>> {

        return flow{
            try {
                val response = apiService.getListMovie(apiKey)
                val data = response.movies
                if (data.isNotEmpty()){
                    emit(ApiResponse.Success(response.movies))
                }else{
                    emit(ApiResponse.Empty)
                }
            }catch (e: Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getSearchMovie(query: String): Flow<ApiResponse<List<MovieResponse>>> {

        return flow{
            try {
                val response = apiService.getSearchMovie(
                    apiKey,
                    "en-US",
                    query
                )
                val data = response.movies
                if (data.isNotEmpty()){
                    emit(ApiResponse.Success(data))
                }else{
                    emit(ApiResponse.Empty)
                }
            }catch (e: Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)

    }
}