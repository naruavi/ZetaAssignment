package com.example.zetamachinecoding.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.zetamachinecoding.domain.Movie
import com.example.zetamachinecoding.databinding.MovieItemBinding

private const val imageBaseUrl = "https://image.tmdb.org/t/p/w500"

class MovieListAdapter(private val movieList: List<Movie>): RecyclerView.Adapter<MovieListAdapter.MovieViewHolder>() {

    class MovieViewHolder(val binding: MovieItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            with(binding) {
                val url = "$imageBaseUrl${movie.backdrop_path}"
                Glide.with(moviePoster).load(url).into(moviePoster)
                name.text = movie.title
                year.text = movie.release_date
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = MovieItemBinding.inflate(LayoutInflater.from(parent.context))
        val holder = MovieViewHolder(binding)
        return holder
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movieList[holder.adapterPosition])
    }
}