package com.gaspol.filmplus.core.data.source.remote.network

import com.gaspol.filmplus.core.data.source.remote.response.ListMovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("movie/popular")
    suspend fun getMovies(@Query("api_key") apiKey: String, @Query("page") page: Int): ListMovieResponse
}