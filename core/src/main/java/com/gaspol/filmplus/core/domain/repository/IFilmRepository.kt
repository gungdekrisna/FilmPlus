package com.gaspol.filmplus.core.domain.repository

import com.gaspol.filmplus.core.data.Resource
import com.gaspol.filmplus.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface IFilmRepository {
    fun getAllMovies(): Flow<com.gaspol.filmplus.core.data.Resource<List<Movie>>>
    fun getFavoriteMovies(): Flow<List<Movie>>
    fun setFavoriteMovie(movie: Movie, state: Boolean)
}