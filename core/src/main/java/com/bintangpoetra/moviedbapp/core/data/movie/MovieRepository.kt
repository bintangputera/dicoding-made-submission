package com.bintangpoetra.moviedbapp.core.data.movie

import com.bintangpoetra.moviedbapp.core.domain.model.Cast
import com.bintangpoetra.moviedbapp.core.domain.model.Movie
import com.bintangpoetra.moviedbapp.core.data.lib.ApiResponse
import com.bintangpoetra.moviedbapp.core.data.movie.local.LocalDataSource
import com.bintangpoetra.moviedbapp.core.data.movie.remote.MovieService
import com.bintangpoetra.moviedbapp.core.domain.mapper.mapToCast
import com.bintangpoetra.moviedbapp.core.domain.mapper.mapToMovie
import com.bintangpoetra.moviedbapp.core.domain.mapper.mapToMovieEntity
import com.bintangpoetra.moviedbapp.core.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import timber.log.Timber

class MovieRepository(
    private val api: MovieService,
    private val localDataSource: LocalDataSource
) : IMovieRepository {

    override fun getPlayingMovies(): Flow<ApiResponse<List<Movie>>> = flow {
        try {
            emit(ApiResponse.Loading)
            val response = api.getPlayingMovies()
            val movieList = response.results

            if (movieList.isNotEmpty()) {
                val movies = mutableListOf<Movie>()
                movieList.forEach { movieResponse ->
                    movies.add(movieResponse.mapToMovie())
                }
                emit(ApiResponse.Success(movies))
            } else {
                emit(ApiResponse.Empty)
            }
        } catch (ex: Exception) {
            emit(ApiResponse.Error(ex.toString()))
            Timber.e(ex.toString())
        }
    }

    override fun getUpcomingMovies(): Flow<ApiResponse<List<Movie>>> = flow {
        try {
            emit(ApiResponse.Loading)
            val response = api.getUpcomingMovies()
            val movieList = response.results

            if (movieList.isNotEmpty()) {
                val movies = mutableListOf<Movie>()
                movieList.forEach { movieResponse ->
                    movies.add(movieResponse.mapToMovie())
                }
                emit(ApiResponse.Success(movies))
            } else {
                emit(ApiResponse.Empty)
            }
        } catch (ex: Exception) {
            emit(ApiResponse.Error(ex.toString()))
            Timber.e(ex.toString())
        }
    }

    override fun getPopularMovies(): Flow<ApiResponse<List<Movie>>> = flow {
        try {
            emit(ApiResponse.Loading)
            val response = api.getPopularMovies()
            val movieList = response.results

            if (movieList.isNotEmpty()) {
                val movies = mutableListOf<Movie>()
                movieList.forEach { movieResponse ->
                    movies.add(movieResponse.mapToMovie())
                }
                emit(ApiResponse.Success(movies))
            } else {
                emit(ApiResponse.Empty)
            }
        } catch (ex: Exception) {
            emit(ApiResponse.Error(ex.toString()))
            Timber.e(ex.toString())
        }
    }

    override fun getMovieCasts(id: Int): Flow<ApiResponse<List<Cast>>> = flow {
        try {
            emit(ApiResponse.Loading)
            val response = api.getMovieCasts(id)
            val castList = response.cast

            if (castList.isNotEmpty()) {
                val cast = mutableListOf<Cast>()
                castList.forEach { castResponse ->
                    cast.add(castResponse.mapToCast())
                }
                emit(ApiResponse.Success(cast))
            } else {
                emit(ApiResponse.Empty)
            }
        } catch (ex: Exception) {
            emit(ApiResponse.Error(ex.toString()))
            Timber.e(ex.toString())
        }
    }

    override fun getMovieByQuery(query: String): Flow<ApiResponse<List<Movie>>> = flow {
        try {
            emit(ApiResponse.Loading)
            val response = api.getMovieByQuery(query)
            val movieList = response.results

            if (movieList.isNotEmpty()) {
                val movies = mutableListOf<Movie>()
                movieList.forEach { movieResponse ->
                    movies.add(movieResponse.mapToMovie())
                }
                emit(ApiResponse.Success(movies))
            } else {
                emit(ApiResponse.Empty)
            }
        } catch (ex: Exception) {
            emit(ApiResponse.Error(ex.toString()))
            Timber.e(ex.toString())
        }
    }

    override fun getAllFavoriteMovies(): Flow<List<Movie>> = flow {
        localDataSource.getAllFavoriteMovies().collect { movie ->
            val movieList = mutableListOf<Movie>()
            movie.forEach { movieEntity ->
                movieList.add(movieEntity.mapToMovie())
            }
            emit(movieList)
        }
    }

    override fun isFavoriteMovie(id: Int): Flow<Boolean> {
        return localDataSource.checkIsFavoriteMovie(id)
    }

    override suspend fun saveMovieAsFavorite(movie: Movie) {
        localDataSource.saveNewFavoriteMovie(movie.mapToMovieEntity())
    }

    override suspend fun deleteMovieFromFavorite(id: Int) {
        localDataSource.deleteFavoriteMovie(id)
    }
}