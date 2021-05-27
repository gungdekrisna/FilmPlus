package com.gaspol.filmplus.core.domain.repository

import com.gaspol.filmplus.core.data.Resource
import com.gaspol.filmplus.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface IFilmRepository {
    fun getAllMovies(): Flow<Resource<List<Movie>>>
    fun getMoviesSearch(query: String): Flow<Resource<List<Movie>>>
    fun getFavoriteMovies(): Flow<List<Movie>>
    fun setFavoriteMovie(movie: Movie, state: Boolean)
}