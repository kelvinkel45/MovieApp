package com.vincode.movieapp.core.utils

import com.vincode.movieapp.core.data.source.local.entity.FavoriteMovieEntity
import com.vincode.movieapp.core.data.source.local.entity.MovieEntity
import com.vincode.movieapp.core.data.source.remote.response.MovieResponse
import com.vincode.movieapp.core.domain.model.Movie

object DataMapper {

    fun mapResponsesToEntities(input: List<MovieResponse>): List<MovieEntity>{
        val movieList = ArrayList<MovieEntity>()
        input.map {
            val movie = MovieEntity(
                movieId = it.id,
                title = it.title,
                poster_path = it.poster_path,
                backdrop_path = it.backdrop_path,
                vote_average = it.vote_average,
                release_date = it.release_date,
                overview = it.overview
            )
            movieList.add(movie)
        }
        return movieList
    }
    
    fun mapEntitiesToDomain(input : List<MovieEntity>) : List<Movie> =
        input.map{
            Movie(
                movieId = it.movieId,
                title = it.title,
                posterPath = it.poster_path,
                backdropPath = it.backdrop_path,
                voteAverage = it.vote_average,
                releaseDate = it.release_date,
                overview = it.overview
            )
        }

    fun mapFavoriteEntitiesToDomain(input : List<FavoriteMovieEntity>) : List<Movie> =
        input.map{
            Movie(
                movieId = it.movieId,
                title = it.title,
                posterPath = it.poster_path,
                backdropPath = it.backdrop_path,
                voteAverage = it.vote_average,
                releaseDate = it.release_date,
                overview = it.overview
            )
        }

    fun mapDomainToEntity(input : Movie) = FavoriteMovieEntity(
        movieId = input.movieId,
        title = input.title,
        poster_path = input.posterPath,
        backdrop_path = input.backdropPath,
        vote_average = input.voteAverage,
        release_date = input.releaseDate,
        overview = input.overview
    )

    fun mapResponsesToDomain(input: List<MovieResponse>) : List<Movie>{
        val searchMovieList = ArrayList<Movie>()
        input.map {
            val searchMovie = Movie(
                movieId = it.id,
                title = it.title,
                posterPath = it.poster_path,
                backdropPath = it.backdrop_path,
                voteAverage = it.vote_average,
                releaseDate = it.release_date,
                overview = it.overview
            )
            searchMovieList.add(searchMovie)
        }
        return searchMovieList
    }

}