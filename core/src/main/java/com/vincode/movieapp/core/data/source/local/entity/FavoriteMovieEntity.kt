package com.vincode.movieapp.core.data.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "favorite_movie")
data class FavoriteMovieEntity(

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "fav_id")
    var movieId: String,

    @ColumnInfo(name = "title")
    var title: String?,

    @ColumnInfo(name = "poster_path")
    var poster_path: String?,

    @ColumnInfo(name = "backdrop_path")
    var backdrop_path: String?,

    @ColumnInfo(name = "vote_average")
    var vote_average: Float,

    @ColumnInfo(name = "release_date")
    var release_date: String?,

    @ColumnInfo(name = "overview")
    var overview: String?,


) : Parcelable


