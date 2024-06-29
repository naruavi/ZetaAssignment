package com.example.zetamachinecoding.data

import com.example.zetamachinecoding.domain.MovieResponse
import retrofit2.http.GET

interface ApiService {

    @GET("3/trending/movie/day?language=en-US")
    suspend fun getTrendingMovies(): MovieResponse
}