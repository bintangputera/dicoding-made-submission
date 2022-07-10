package com.bintangpoetra.moviedbapp.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.bintangpoetra.moviedbapp.core.domain.model.Cast
import com.bintangpoetra.moviedbapp.core.data.lib.ApiResponse
import com.bintangpoetra.moviedbapp.core.domain.MovieUseCase
import com.bintangpoetra.moviedbapp.core.domain.model.Movie
import kotlinx.coroutines.launch

class DetailViewModel(private val movieUseCase: MovieUseCase): ViewModel() {

    fun getMovieCasts(id: Int) : LiveData<ApiResponse<List<Cast>>> {
        return movieUseCase.getMovieCasts(id).asLiveData()
    }

    fun checkIsFavoriteMovie(id: Int): LiveData<Boolean> {
        return movieUseCase.isFavoriteMovie(id).asLiveData()
    }

    fun saveMovieAsFavorite(movie: Movie) {
        viewModelScope.launch {
            movieUseCase.saveMovieAsFavorite(movie)
        }
    }

    fun deleteMovieFromFavorite(id: Int) {
        viewModelScope.launch {
            movieUseCase.deleteMovieFromFavorite(id)
        }
    }

}