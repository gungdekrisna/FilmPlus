package com.gaspol.filmplus.core.ui.utils

import com.gaspol.filmplus.core.domain.model.Movie
import com.gaspol.filmplus.core.ui.model.MoviePresentation

object DataMapper {
    fun mapDomainToPresentation(input: List<Movie>): List<MoviePresentation> =
        input.map {
            MoviePresentation(
                autoId = it.autoId,
                id = it.id,
                title = it.title,
                posterPath = it.posterPath,
                releaseDate = it.releaseDate,
                overview = it.overview,
                backdropPath = it.backdropPath,
                voteAverage = it.voteAverage,
                favorite = it.favorite
            )
        }

    fun mapPresentationToDomain(input: MoviePresentation) =
        Movie(
            autoId = input.autoId,
            id = input.id,
            title = input.title,
            posterPath = input.posterPath,
            releaseDate = input.releaseDate,
            overview = input.overview,
            backdropPath = input.backdropPath,
            voteAverage = input.voteAverage,
            favorite = input.favorite
        )
}