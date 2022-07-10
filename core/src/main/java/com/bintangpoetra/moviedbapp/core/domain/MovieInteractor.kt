package com.bintangpoetra.moviedbapp.core.domain

import com.bintangpoetra.moviedbapp.core.domain.model.Cast
import com.bintangpoetra.moviedbapp.core.domain.model.Movie
import com.bintangpoetra.moviedbapp.core.data.lib.ApiResponse
import com.bintangpoetra.moviedbapp.core.data.movie.MovieRepository
import kotlinx.coroutines.flow.Flow

class MovieInteractor(private val movieRepository: MovieRepository): MovieUseCase {

    override fun getPlayingMovies(): Flow<ApiResponse<List<Movie>>> {
        return movieRepository.getPlayingMovies()
    }

    override fun getUpcomingMovies(): Flow<ApiResponse<List<Movie>>> {
        return movieRepository.getUpcomingMovies()
    }

    override fun getPopularMovies(): Flow<ApiResponse<List<Movie>>> {
        return movieRepository.getPopularMovies()
    }

    override fun getMovieCasts(id: Int): Flow<ApiResponse<List<Cast>>> {
        return movieRepository.getMovieCasts(id)
    }

    override fun getAllFavoriteMovies(): Flow<List<Movie>> {
        return movieRepository.getAllFavoriteMovies()
    }

    override fun getMovieByQuery(query: String): Flow<ApiResponse<List<Movie>>> {
        return movieRepository.getMovieByQuery(query)
    }

    override fun isFavoriteMovie(id: Int): Flow<Boolean> {
        return movieRepository.isFavoriteMovie(id)
    }

    override suspend fun saveMovieAsFavorite(movie: Movie) {
        return movieRepository.saveMovieAsFavorite(movie)
    }

    override suspend fun deleteMovieFromFavorite(id: Int) {
        return movieRepository.deleteMovieFromFavorite(id)
    }
}