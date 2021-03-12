package com.vincode.movieapp.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.vincode.movieapp.core.domain.usecase.MovieUseCase

class FavoriteViewModel (movieUseCase: MovieUseCase) : ViewModel(){
    val favoriteMovies = movieUseCase.getFavoriteMovie().asLiveData()
}