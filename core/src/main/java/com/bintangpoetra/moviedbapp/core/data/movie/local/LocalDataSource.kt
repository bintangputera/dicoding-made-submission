package com.bintangpoetra.moviedbapp.core.data.movie.local

import com.bintangpoetra.moviedbapp.core.data.movie.local.room.MovieDao
import com.bintangpoetra.moviedbapp.core.data.movie.local.room.MovieEntity
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val dao: MovieDao) {

    fun getAllFavoriteMovies(): Flow<List<MovieEntity>> = dao.getAllFavoriteMovies()

    fun checkIsFavoriteMovie(id: Int): Flow<Boolean> = dao.isFavoriteMovie(id)

    suspend fun saveNewFavoriteMovie(movieEntity: MovieEntity) {
        dao.saveNewFavoriteMovie(movieEntity)
    }

    suspend fun deleteFavoriteMovie(id: Int) {
        dao.deleteFavoriteMovie(id)
    }

}