package com.bintangpoetra.moviedbapp.di

import com.bintangpoetra.moviedbapp.favorite.FavoriteViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoriteModule = module {

    viewModel { FavoriteViewModel(get()) }

}