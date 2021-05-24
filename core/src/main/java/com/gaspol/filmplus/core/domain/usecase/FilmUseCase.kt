package com.gaspol.filmplus.core.domain.usecase

import com.gaspol.filmplus.core.data.Resource
import com.gaspol.filmplus.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface FilmUseCase {
    fun getAllMovies(): Flow<Resource<List<Movie>>>
    fun getFavoriteMovies(): Flow<List<Movie>>
    fun setFavoriteMovie(movie: Movie, state: Boolean)
}