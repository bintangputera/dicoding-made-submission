package com.bintangpoetra.moviedbapp.core.di

import com.bintangpoetra.moviedbapp.core.data.movie.MovieRepository
import com.bintangpoetra.moviedbapp.core.data.movie.local.LocalDataSource
import org.koin.dsl.module

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { MovieRepository(get(), get()) }
}