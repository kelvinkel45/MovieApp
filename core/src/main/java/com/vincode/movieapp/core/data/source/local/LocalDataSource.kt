package com.vincode.movieapp.core.data.source.local

import com.vincode.movieapp.core.data.source.local.entity.FavoriteMovieEntity
import com.vincode.movieapp.core.data.source.local.entity.MovieEntity
import com.vincode.movieapp.core.data.source.local.room.MovieDao
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class LocalDataSource(private val movieDao: MovieDao){


    fun getAllMovies(): Flow<List<MovieEntity>> = movieDao.getAllMovies()

    fun getFavoriteMovie(): Flow<List<FavoriteMovieEntity>> = movieDao.getFavoriteMovie()

    suspend fun insertMovie(movieList : List<MovieEntity>) = movieDao.insertMovie(movieList)

    fun setFavoriteMovie(movieEntity: FavoriteMovieEntity){
        GlobalScope.launch {
            if (movieDao.getMovie(movieEntity.movieId) == 0) {
                movieDao.insertFavoriteMovie(movieEntity)
            } else {
                movieDao.deleteFavoriteMovie(movieEntity)
            }
        }
    }

    fun isFavorite(movieId: String): Flow<Boolean>{
        return movieDao.isFavorite(movieId)
    }

}