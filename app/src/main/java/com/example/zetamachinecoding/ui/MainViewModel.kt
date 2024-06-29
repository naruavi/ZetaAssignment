package com.example.zetamachinecoding.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.zetamachinecoding.data.ApiService
import com.example.zetamachinecoding.domain.Movie
import com.example.zetamachinecoding.data.MoviesRepositoryImpl
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val authToken =
    "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxNjg4ZTI4MWRjYTVmOWM0NGY4NzFhNmVhYTJmODMxMCIsIm5iZiI6MTcxOTU2NTAzMS4zODgzNDcsInN1YiI6IjY2N2U3YTcyZGI2Njc1YzMyYjhkNzI0NiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.aZtcEIuonqZH3BFArwQ3Z0QX5ysYpPaUglyINAFuLBo"

class MainViewModel : ViewModel() {

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val request = chain.request().newBuilder()
            request.header("Authorization", "Bearer $authToken").build()
            request.header("Content-Type", "application/json")
            chain.proceed(request.build())
        }
        .build()

    private val retrofitClient = Retrofit.Builder()
        .baseUrl("http://api.themoviedb.org/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    private val repository = MoviesRepositoryImpl(retrofitClient.create(ApiService::class.java))

    val trendingMovieList = MutableLiveData<List<Movie>>()

    fun getTrendingMovies(page: String = "day") {
        viewModelScope.launch {
            trendingMovieList.value = repository.fetchMovies(page).results
        }
    }

}