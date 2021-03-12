package com.vincode.movieapp.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vincode.movieapp.core.data.source.local.entity.FavoriteMovieEntity
import com.vincode.movieapp.core.data.source.local.entity.MovieEntity

@Database(entities = [MovieEntity::class, FavoriteMovieEntity::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

}