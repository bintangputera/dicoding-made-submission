package com.bintangpoetra.moviedbapp.presentation.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.bintangpoetra.moviedbapp.core.data.lib.ApiResponse
import com.bintangpoetra.moviedbapp.core.domain.MovieUseCase
import com.bintangpoetra.moviedbapp.core.domain.model.Movie

class ListViewModel(private val movieUseCase: MovieUseCase): ViewModel() {

    fun getPopularMovies(): LiveData<ApiResponse<List<Movie>>> {
        return movieUseCase.getPopularMovies().asLiveData()
    }

    fun getPlayingMovies(): LiveData<ApiResponse<List<Movie>>> {
        return movieUseCase.getPlayingMovies().asLiveData()
    }

}