package com.gaspol.filmplus.core.data.source.local.room

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface FilmDao {

    @Query("SELECT * FROM movies")
    fun getAllMovies(): Flow<List<com.gaspol.filmplus.core.data.source.local.entity.MovieEntity>>

    @Query("SELECT * FROM movies where favorite = 1")
    fun getFavoriteMovie(): Flow<List<com.gaspol.filmplus.core.data.source.local.entity.MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: List<com.gaspol.filmplus.core.data.source.local.entity.MovieEntity>)

    @Update
    fun updateFavoriteMovie(movie: com.gaspol.filmplus.core.data.source.local.entity.MovieEntity)
}