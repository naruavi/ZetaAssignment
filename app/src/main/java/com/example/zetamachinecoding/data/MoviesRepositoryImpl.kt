package com.example.zetamachinecoding.data

import com.example.zetamachinecoding.domain.MovieResponse
import com.example.zetamachinecoding.domain.MoviesRepository

class MoviesRepositoryImpl(val service: ApiService): MoviesRepository {
    override suspend fun fetchMovies(): MovieResponse {
        //context switching is not required since retrofit will do it.
        return service.getTrendingMovies()
    }
}