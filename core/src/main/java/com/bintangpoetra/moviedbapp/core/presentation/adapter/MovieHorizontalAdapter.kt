package com.bintangpoetra.moviedbapp.core.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bintangpoetra.moviedbapp.core.domain.model.Movie
import com.bintangpoetra.moviedbapp.core.databinding.ItemMovieHorizontalBinding
import com.bintangpoetra.moviedbapp.core.utils.setImageFromUrl

class MovieHorizontalAdapter(private val movieList: List<Movie>, private val onClick: (Movie) -> Unit) : RecyclerView.Adapter<MovieHorizontalAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = ItemMovieHorizontalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        movieList[position].let {
            holder.bind(it)
        }
    }

    override fun getItemCount(): Int = 10

    inner class MovieViewHolder(private val binding: ItemMovieHorizontalBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            binding.apply {
                imgThumbnail.setImageFromUrl(movie.posterPath.toString())
                tvMovieTitle.text = movie.title
                tvMovieRating.text = movie.voteAverage.toString()

            }
            binding.root.setOnClickListener {
                onClick(movie)
            }
        }
    }

}