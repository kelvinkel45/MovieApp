package com.vincode.movieapp.core.data.source.remote.network

import com.vincode.movieapp.core.data.source.remote.response.ListMovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("discover/movie")
    suspend fun getListMovie (
        @Query("api_key") api_key : String,
        @Query("language") language : String = "en-US",
        @Query("page") page : String = "1"
    ) : ListMovieResponse

    @GET("search/movie")
    suspend fun getSearchMovie(
        @Query("api_key") api_key: String,
        @Query("language") language : String,
        @Query("query") query : String,
        @Query("page") page : String = "1"
    ) : ListMovieResponse
}