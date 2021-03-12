package com.vincode.movieapp.core.domain.usecase

import com.vincode.movieapp.core.data.Resource
import com.vincode.movieapp.core.domain.model.Movie
import com.vincode.movieapp.core.domain.repository.IMovieRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.flow.Flow


class MovieInteractor (private val movieRepository: IMovieRepository) : MovieUseCase{
    override fun getAllMovies(): Flow<Resource<List<Movie>>> {
        return movieRepository.getAllMovies()
    }

    override fun getFavoriteMovie(): Flow<List<Movie>> {
        return movieRepository.getFavoriteMovie()
    }

    override fun setFavoriteMovie(movie: Movie) {
        return movieRepository.setFavoriteMovie(movie)
    }

    @ExperimentalCoroutinesApi
    @FlowPreview
    override fun getSearchMovie(query: BroadcastChannel<String>): Flow<Resource<List<Movie>>> {
        return movieRepository.getSearchMovie(query)
    }

    override fun isFavorite(movieId: String): Flow<Boolean> {
        return movieRepository.isFavorite(movieId)
    }


}