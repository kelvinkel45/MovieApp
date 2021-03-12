package com.vincode.movieapp.core.data.source.local.room

import androidx.room.*
import com.vincode.movieapp.core.data.source.local.entity.FavoriteMovieEntity
import com.vincode.movieapp.core.data.source.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao{

    @Query("SELECT * FROM movie")
    fun getAllMovies(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM favorite_movie")
    fun getFavoriteMovie(): Flow<List<FavoriteMovieEntity>>

    @Query("SELECT * FROM movie where movie_id = :movieId")
    fun getDetailMovie(movieId: String): Flow<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movieEntities: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteMovie(favoriteMovieEntity: FavoriteMovieEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetailMovie(movieEntity: MovieEntity)

    @Query("SELECT COUNT(*) FROM favorite_movie WHERE fav_id = :id")
    suspend fun getMovie(id: String): Int

    @Update
    fun updateFavoriteMovie(movieEntity: MovieEntity)

    @Delete
    fun deleteFavoriteMovie(movieEntity: FavoriteMovieEntity)

    @Query("SELECT EXISTS(SELECT * FROM favorite_movie WHERE fav_id = :id)")
    fun isFavorite(id: String): Flow<Boolean>

}