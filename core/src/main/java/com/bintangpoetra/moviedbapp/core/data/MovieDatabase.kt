package com.bintangpoetra.moviedbapp.core.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bintangpoetra.moviedbapp.core.data.movie.local.room.MovieDao
import com.bintangpoetra.moviedbapp.core.data.movie.local.room.MovieEntity

@Database(
    entities = [MovieEntity::class],
    version = 1,
    exportSchema = false
)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

}