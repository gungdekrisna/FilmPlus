package com.gaspol.filmplus.core.data

import com.gaspol.filmplus.core.data.source.remote.network.ApiResponse
import com.gaspol.filmplus.core.data.source.remote.response.MovieResponse
import com.gaspol.filmplus.core.domain.model.Movie
import com.gaspol.filmplus.core.domain.repository.IFilmRepository
import com.gaspol.filmplus.core.utils.AppExecutors
import com.gaspol.filmplus.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FilmRepository(
    private val remoteDataSource: com.gaspol.filmplus.core.data.source.remote.RemoteDataSource,
    private val localDataSource: com.gaspol.filmplus.core.data.source.local.LocalDataSource,
    private val appExecutors: AppExecutors
) : IFilmRepository {
    override fun getAllMovies(): Flow<Resource<List<Movie>>> {
        return object : NetworkBoundResource<List<Movie>, List<MovieResponse>>() {
            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getAllMovies().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean =
                data!!.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getAllMovies()

            override suspend fun saveCallResult(data: List<MovieResponse>) {
                val movieList = DataMapper.mapResponsesToEntities(data)
                appExecutors.diskIO().execute { localDataSource.insertMovie(movieList) }
            }
        }.asFlow()
    }

    override fun getMoviesSearch(query: String): Flow<Resource<List<Movie>>> {
        return object : NetworkBoundResource<List<Movie>, List<MovieResponse>>() {
            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getAllMovies().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean =
                true

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getMoviesSearch(query)

            override suspend fun saveCallResult(data: List<MovieResponse>) {
                val movieList = DataMapper.mapResponsesToEntities(data)
                appExecutors.diskIO().execute { localDataSource.insertMovie(movieList) }
            }

        }.asFlow()
    }

    override fun getFavoriteMovies(): Flow<List<Movie>> {
        return localDataSource.getFavoriteMovie().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteMovie(movie: Movie, state: Boolean) {
        val movieEntity = DataMapper.mapDomainToEntity(movie)
        appExecutors.diskIO().execute { localDataSource.setFavoriteMovie(movieEntity, state) }
    }
}