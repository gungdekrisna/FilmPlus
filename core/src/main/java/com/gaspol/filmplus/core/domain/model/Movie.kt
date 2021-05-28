package com.gaspol.filmplus.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie (
    var autoId: Int?,
    var id: Int,
    val title: String,
    val posterPath: String?,
    val releaseDate: String?,
    val overview: String,
    val backdropPath: String?,
    val voteAverage: Double,
    var favorite: Boolean
) : Parcelable