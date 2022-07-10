package com.bintangpoetra.moviedbapp.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.bintangpoetra.moviedbapp.core.domain.MovieUseCase
import com.bintangpoetra.moviedbapp.core.domain.model.Movie

class FavoriteViewModel(private val movieUseCase: MovieUseCase): ViewModel() {

    fun getAllFavoriteMovies(): LiveData<List<Movie>> {
        return movieUseCase.getAllFavoriteMovies().asLiveData()
    }

}