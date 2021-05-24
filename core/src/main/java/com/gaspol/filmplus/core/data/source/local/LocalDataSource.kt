package com.gaspol.filmplus.core.data.source.local

import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val filmDao: com.gaspol.filmplus.core.data.source.local.room.FilmDao) {
    fun getAllMovies(): Flow<List<com.gaspol.filmplus.core.data.source.local.entity.MovieEntity>> = filmDao.getAllMovies()

    fun getFavoriteMovie(): Flow<List<com.gaspol.filmplus.core.data.source.local.entity.MovieEntity>> = filmDao.getFavoriteMovie()

    fun insertMovie(movieList: List<com.gaspol.filmplus.core.data.source.local.entity.MovieEntity>) = filmDao.insertMovie(movieList)

    fun setFavoriteMovie(movie: com.gaspol.filmplus.core.data.source.local.entity.MovieEntity, newState: Boolean) {
        movie.favorite = newState
        filmDao.updateFavoriteMovie(movie)
    }
}