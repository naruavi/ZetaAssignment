package com.example.zetamachinecoding.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.zetamachinecoding.R
import com.example.zetamachinecoding.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.trendingList.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        binding.week.setOnClickListener {
            viewModel.getTrendingMovies("week")
            binding.day.setBackgroundColor(ContextCompat.getColor(this, R.color.white))
            binding.week.setBackgroundColor(ContextCompat.getColor(this, R.color.black))
            binding.day.setTextColor(ContextCompat.getColor(this, R.color.black))
            binding.day.setTextColor(ContextCompat.getColor(this, R.color.white))
        }
        binding.day.setOnClickListener {
            viewModel.getTrendingMovies("day")
            binding.day.setBackgroundColor(ContextCompat.getColor(this, R.color.black))
            binding.week.setBackgroundColor(ContextCompat.getColor(this, R.color.white))
            binding.day.setTextColor(ContextCompat.getColor(this, R.color.white))
            binding.day.setTextColor(ContextCompat.getColor(this, R.color.black))
        }
        viewModel.trendingMovieList.observe(this) {
            with(binding) {
                trendingList.adapter = MovieListAdapter(it)
            }
        }
        viewModel.getTrendingMovies()


    }
}