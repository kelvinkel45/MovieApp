package com.vincode.movieapp.presentation.detail

import androidx.lifecycle.*
import com.vincode.movieapp.core.domain.model.Movie
import com.vincode.movieapp.core.domain.usecase.MovieUseCase


class DetailMovieViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {

    fun isFavorite(movieId: String): LiveData<Boolean> = movieUseCase.isFavorite(movieId).asLiveData()

    fun setFavoriteMovie(movie: Movie) = movieUseCase.setFavoriteMovie(movie)
}