package com.bintangpoetra.moviedbapp.core.domain.repository

import com.bintangpoetra.moviedbapp.core.domain.model.Cast
import com.bintangpoetra.moviedbapp.core.data.lib.ApiResponse
import com.bintangpoetra.moviedbapp.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {

    fun getPlayingMovies(): Flow<ApiResponse<List<Movie>>>

    fun getUpcomingMovies(): Flow<ApiResponse<List<Movie>>>

    fun getPopularMovies(): Flow<ApiResponse<List<Movie>>>

    fun getMovieCasts(id: Int): Flow<ApiResponse<List<Cast>>>

    fun getMovieByQuery(query: String): Flow<ApiResponse<List<Movie>>>

    fun getAllFavoriteMovies(): Flow<List<Movie>>

    fun isFavoriteMovie(id: Int): Flow<Boolean>

    suspend fun saveMovieAsFavorite(movie: Movie)

    suspend fun deleteMovieFromFavorite(id: Int)
}