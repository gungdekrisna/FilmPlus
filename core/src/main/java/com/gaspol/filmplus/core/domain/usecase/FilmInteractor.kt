package com.gaspol.filmplus.core.domain.usecase

import com.gaspol.filmplus.core.data.Resource
import com.gaspol.filmplus.core.domain.model.Movie
import com.gaspol.filmplus.core.domain.repository.IFilmRepository
import kotlinx.coroutines.flow.Flow

class FilmInteractor(private val filmRepository: IFilmRepository): FilmUseCase {
    override fun getAllMovies(): Flow<Resource<List<Movie>>> = filmRepository.getAllMovies()

    override fun getMoviesSearch(query: String): Flow<Resource<List<Movie>>> = filmRepository.getMoviesSearch(query)

    override fun getFavoriteMovies(): Flow<List<Movie>> = filmRepository.getFavoriteMovies()

    override fun setFavoriteMovie(movie: Movie, state: Boolean) = filmRepository.setFavoriteMovie(movie, state)
}