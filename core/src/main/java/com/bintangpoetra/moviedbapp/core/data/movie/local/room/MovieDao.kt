package com.bintangpoetra.moviedbapp.core.data.movie.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("SELECT * FROM favorite_movies")
    fun getAllFavoriteMovies(): Flow<List<MovieEntity>>

    @Query("SELECT EXISTS(SELECT * FROM favorite_movies WHERE id = :id)")
    fun isFavoriteMovie(id: Int): Flow<Boolean>

    @Query("DELETE FROM favorite_movies WHERE id = :id")
    suspend fun deleteFavoriteMovie(id: Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveNewFavoriteMovie(movieEntity: MovieEntity)

}