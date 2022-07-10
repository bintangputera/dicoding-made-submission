package com.bintangpoetra.moviedbapp.core.domain

import com.bintangpoetra.moviedbapp.core.domain.model.Cast
import com.bintangpoetra.moviedbapp.core.domain.model.Movie
import com.bintangpoetra.moviedbapp.core.data.lib.ApiResponse
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {
    fun getPlayingMovies(): Flow<ApiResponse<List<Movie>>>

    fun getUpcomingMovies(): Flow<ApiResponse<List<Movie>>>

    fun getPopularMovies(): Flow<ApiResponse<List<Movie>>>

    fun getMovieCasts(id: Int): Flow<ApiResponse<List<Cast>>>

    fun getAllFavoriteMovies(): Flow<List<Movie>>

    fun getMovieByQuery(query: String): Flow<ApiResponse<List<Movie>>>

    fun isFavoriteMovie(id: Int): Flow<Boolean>

    suspend fun saveMovieAsFavorite(movie: Movie)

    suspend fun deleteMovieFromFavorite(id: Int)
}