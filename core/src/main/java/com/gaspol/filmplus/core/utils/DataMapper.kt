package com.gaspol.filmplus.core.utils

import com.gaspol.filmplus.core.data.source.local.entity.MovieEntity
import com.gaspol.filmplus.core.data.source.remote.response.MovieResponse
import com.gaspol.filmplus.core.domain.model.Movie

object DataMapper {
    fun mapResponsesToEntities(input: List<MovieResponse>): List<MovieEntity> {
        val movieList = ArrayList<MovieEntity>()
        input.map {
            val movie = MovieEntity(
                id = it.id,
                title = it.title,
                posterPath = it.posterPath,
                releaseDate = it.releaseDate,
                overview = it.overview,
                backdropPath = it.backdropPath,
                voteAverage = it.voteAverage,
                favorite = false
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapEntitiesToDomain(input: List<MovieEntity>): List<Movie> =
        input.map {
            Movie(
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

    fun mapDomainToEntity(input: Movie) =
        MovieEntity(
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