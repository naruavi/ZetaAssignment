package com.example.zetamachinecoding.domain

import com.example.zetamachinecoding.domain.MovieResponse

interface MoviesRepository {

    suspend fun fetchMovies(): MovieResponse
}