package com.bintangpoetra.moviedbapp.core.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bintangpoetra.moviedbapp.core.databinding.ItemListFavoriteMovieBinding
import com.bintangpoetra.moviedbapp.core.domain.model.Movie
import com.bintangpoetra.moviedbapp.core.presentation.adapter.FavoriteAdapter.FavoriteViewHolder
import com.bintangpoetra.moviedbapp.core.utils.setImageFromUrl

class FavoriteAdapter(private val movieList: List<Movie>, private val onClick: (Movie) -> Unit): RecyclerView.Adapter<FavoriteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val view = ItemListFavoriteMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        movieList[position].let {
            holder.bind(it)
        }
    }

    override fun getItemCount(): Int = movieList.size

    inner class FavoriteViewHolder(private val binding: ItemListFavoriteMovieBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            binding.apply {
                tvMovieTitle.text = movie.title
                tvMovieOverview.text = movie.overview
                tvMovieRating.text = movie.voteAverage.toString()
                movie.posterPath?.let { imgThumbnail.setImageFromUrl(it) }

                root.setOnClickListener {
                    onClick(movie)
                }
            }
        }
    }

}