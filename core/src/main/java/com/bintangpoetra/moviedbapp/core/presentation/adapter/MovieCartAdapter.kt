package com.bintangpoetra.moviedbapp.core.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bintangpoetra.moviedbapp.core.databinding.ItemCardHorizontalBinding
import com.bintangpoetra.moviedbapp.core.domain.model.Movie
import com.bintangpoetra.moviedbapp.core.utils.setImageFromUrl

class MovieCartAdapter(private val movieList: List<Movie>, private val onClick: (Movie) -> Unit): RecyclerView.Adapter<MovieCartAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = ItemCardHorizontalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        movieList[position].let {
            holder.bind(it)
        }
    }

    override fun getItemCount(): Int = 5

    inner class MovieViewHolder(private val binding: ItemCardHorizontalBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            binding.apply {
                imgThumbnail.setImageFromUrl(movie.posterPath.toString())
                tvMovieTitle.text = movie.title

            }
            binding.root.setOnClickListener {
                onClick(movie)
            }
        }
    }

}