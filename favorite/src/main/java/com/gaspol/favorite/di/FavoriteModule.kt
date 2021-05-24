package com.gaspol.favorite.di

import com.gaspol.favorite.FavoriteViewModel
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel

val favoriteModule = module {
    viewModel { FavoriteViewModel(get()) }
}