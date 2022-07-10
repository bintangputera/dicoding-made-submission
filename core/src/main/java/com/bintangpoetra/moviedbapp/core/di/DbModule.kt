package com.bintangpoetra.moviedbapp.core.di

import android.app.Application
import androidx.room.Room
import com.bintangpoetra.moviedbapp.core.data.MovieDatabase
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

const val DB_NAME: String = "movie-database"

val dbModule = module {

    factory { get<MovieDatabase>().movieDao() }

    fun provideDatabase(application: Application): MovieDatabase {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("movie_app".toCharArray())
        val factory = SupportFactory(passphrase)
        return Room.databaseBuilder(application, MovieDatabase::class.java, DB_NAME)
            .fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }

    single { provideDatabase(androidApplication()) }

}