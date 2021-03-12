package com.vincode.movieapp.core.domain.usecase

import com.vincode.movieapp.core.data.Resource
import com.vincode.movieapp.core.domain.model.Movie
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.flow.Flow


interface MovieUseCase {

    fun getAllMovies() : Flow<Resource<List<Movie>>>

    fun getFavoriteMovie() : Flow<List<Movie>>

    fun setFavoriteMovie(movie: Movie)

    @FlowPreview
    @ExperimentalCoroutinesApi
    fun getSearchMovie(query : BroadcastChannel<String>) : Flow<Resource<List<Movie>>>

    fun isFavorite(movieId : String) : Flow<Boolean>
}