package com.gaspol.filmplus.di

import com.gaspol.filmplus.core.domain.usecase.FilmInteractor
import com.gaspol.filmplus.core.domain.usecase.FilmUseCase
import com.gaspol.filmplus.detail.DetailViewModel
import com.gaspol.filmplus.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<FilmUseCase> { FilmInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}