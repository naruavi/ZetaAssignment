package com.example.zetamachinecoding.domain

interface MoviesRepository {

    suspend fun fetchMovies(page: String): MovieResponse
}