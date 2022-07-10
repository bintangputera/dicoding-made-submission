package com.bintangpoetra.moviedbapp.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.bintangpoetra.moviedbapp.core.domain.model.Movie
import com.bintangpoetra.moviedbapp.core.data.lib.ApiResponse
import com.bintangpoetra.moviedbapp.core.domain.MovieUseCase

class MainViewModel(private val movieUseCase: MovieUseCase): ViewModel() {

    fun getUpcomingMovies(): LiveData<ApiResponse<List<Movie>>> {
        return movieUseCase.getUpcomingMovies().asLiveData()
    }

    fun getPopularMovies(): LiveData<ApiResponse<List<Movie>>> {
        return movieUseCase.getPopularMovies().asLiveData()
    }

    fun getPlayingMovies(): LiveData<ApiResponse<List<Movie>>> {
        return movieUseCase.getPlayingMovies().asLiveData()
    }

}