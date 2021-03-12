package com.vincode.movieapp.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie (
    var movieId : String,
    var title : String? ="",
    var posterPath : String? ="",
    var backdropPath : String? ="",
    var voteAverage : Float,
    var releaseDate : String? ="",
    var overview : String? =""
) : Parcelable