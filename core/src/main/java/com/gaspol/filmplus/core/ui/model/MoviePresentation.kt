package com.gaspol.filmplus.core.ui.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MoviePresentation (
    var id: Int,
    val title: String,
    val posterPath: String,
    val releaseDate: String,
    val overview: String,
    val backdropPath: String,
    val voteAverage: Double,
    var favorite: Boolean
) : Parcelable