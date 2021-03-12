package com.vincode.movieapp.presentation.search

import androidx.lifecycle.*
import com.vincode.movieapp.core.domain.usecase.MovieUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel

@FlowPreview
@ExperimentalCoroutinesApi
class SearchViewModel(movieUseCase: MovieUseCase) :ViewModel() {

    val queryChannel = BroadcastChannel<String>(Channel.CONFLATED)
    val searchMovies = movieUseCase.getSearchMovie(queryChannel).asLiveData()

}