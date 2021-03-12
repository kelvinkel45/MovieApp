package com.vincode.movieapp.di

import com.vincode.movieapp.core.domain.usecase.MovieInteractor
import com.vincode.movieapp.core.domain.usecase.MovieUseCase
import com.vincode.movieapp.presentation.detail.DetailMovieViewModel
import com.vincode.movieapp.presentation.main.MainViewModel
import com.vincode.movieapp.presentation.search.SearchViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MovieUseCase>{ MovieInteractor(get()) }
}

@ExperimentalCoroutinesApi
@FlowPreview
val viewModelModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { DetailMovieViewModel(get()) }
    viewModel { SearchViewModel(get()) }
}