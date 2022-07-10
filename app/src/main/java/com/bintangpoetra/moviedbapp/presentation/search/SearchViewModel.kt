package com.bintangpoetra.moviedbapp.presentation.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.bintangpoetra.moviedbapp.core.data.lib.ApiResponse
import com.bintangpoetra.moviedbapp.core.domain.MovieUseCase
import com.bintangpoetra.moviedbapp.core.domain.model.Movie

class SearchViewModel(private val movieUseCase: MovieUseCase): ViewModel() {

    fun getMoviesByQuery(query: String): LiveData<ApiResponse<List<Movie>>> {
        return movieUseCase.getMovieByQuery(query).asLiveData()
    }

}