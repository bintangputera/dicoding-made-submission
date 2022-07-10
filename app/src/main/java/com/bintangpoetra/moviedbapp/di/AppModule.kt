package com.bintangpoetra.moviedbapp.di

import com.bintangpoetra.moviedbapp.core.domain.MovieInteractor
import com.bintangpoetra.moviedbapp.core.domain.MovieUseCase
import com.bintangpoetra.moviedbapp.presentation.detail.DetailViewModel
import com.bintangpoetra.moviedbapp.presentation.list.ListViewModel
import com.bintangpoetra.moviedbapp.presentation.main.MainViewModel
import com.bintangpoetra.moviedbapp.presentation.search.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MovieUseCase> { MovieInteractor(get()) }
}

val viewModelModule = module {

    viewModel { MainViewModel(get()) }
    viewModel { DetailViewModel(get()) }
    viewModel { ListViewModel(get()) }
    viewModel { SearchViewModel(get()) }

}