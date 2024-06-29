package com.example.zetamachinecoding.data

import com.example.zetamachinecoding.domain.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("3/trending/movie/{page}?language=en-US")
    suspend fun getTrendingMovies(@Path("page")page: String): MovieResponse
}